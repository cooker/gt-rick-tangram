package com.github.cooker.core.utils;

import com.github.cooker.core.SnowFlake;

/**
 * grant
 * 27/5/2020 2:41 下午
 * 描述：
 */
public class SnowFlakeFactory {
    volatile static SnowFlakeFactory factory = null;
    volatile SnowFlake.SerialNo.Builder snowFlake = null;

    /**
     * 起始的时间戳
     */
    private final static long START_STMP = 1480166465631L;

    /**
     * 每一部分占用的位数
     */
    private final static long SEQUENCE_BIT = 12; //序列号占用的位数
    private final static long MACHINE_BIT = 5;   //机器标识占用的位数
    private final static long DATACENTER_BIT = 5;//数据中心占用的位数

    /**
     * 每一部分的最大值
     */
    private final static long MAX_DATACENTER_NUM = -1L ^ (-1L << DATACENTER_BIT);
    private final static long MAX_MACHINE_NUM = -1L ^ (-1L << MACHINE_BIT);
    private final static long MAX_SEQUENCE = -1L ^ (-1L << SEQUENCE_BIT);

    /**
     * 每一部分向左的位移
     */
    private final static long MACHINE_LEFT = SEQUENCE_BIT;
    private final static long DATACENTER_LEFT = SEQUENCE_BIT + MACHINE_BIT;
    private final static long TIMESTMP_LEFT = DATACENTER_LEFT + DATACENTER_BIT;

    public static SnowFlakeFactory getInstance(long datacenterId, long machineId) {
        if (factory == null) {
            synchronized (SnowFlakeFactory.class){
                if (factory == null){
                    factory = new SnowFlakeFactory();
                    factory.snowFlake = makeSnowFlake(datacenterId, machineId);
                }
            }
        }
        return factory;
    }

    public static SnowFlake.SerialNo parse(long serialNo){
        SnowFlake.SerialNo.Builder builder = SnowFlake.SerialNo.newBuilder();

        builder.setLastStmp(serialNo >>> TIMESTMP_LEFT);
        builder.setDatacenterId((serialNo >>> DATACENTER_LEFT) & 0x1F);
        builder.setMachineId((serialNo >>> MACHINE_LEFT ) & 0x1F);
        builder.setSequence(serialNo & 0xFFF);

        return builder.build();
    }

    protected static SnowFlake.SerialNo.Builder makeSnowFlake(long datacenterId, long machineId){
        SnowFlake.SerialNo.Builder builder = SnowFlake.SerialNo.newBuilder();

        return builder
                .setDatacenterId(datacenterId)
                .setMachineId(machineId)
                .setSequence(0L)
                .setLastStmp(-1L);
    }

    /**
     * 产生下一个ID
     *
     * @return
     */
    public synchronized long next() {
        long currStmp = getNewstmp();
        long lastStmp = snowFlake.getLastStmp();
        if (currStmp < lastStmp) {
            throw new RuntimeException("Clock moved backwards.  Refusing to generate id");
        }

        if (currStmp == lastStmp) {
            //相同毫秒内，序列号自增
            snowFlake.setSequence((snowFlake.getSequence() + 1) & MAX_SEQUENCE);
            //同一毫秒的序列数已经达到最大
            if (snowFlake.getSequence() == 0L) {
                currStmp = getNextMill();
            }
        } else {
            //不同毫秒内，序列号置为0
            snowFlake.setSequence(0L);
        }

        lastStmp = currStmp;
        snowFlake.setLastStmp(lastStmp);
        return (currStmp - START_STMP) << TIMESTMP_LEFT //时间戳部分
                | snowFlake.getDatacenterId() << DATACENTER_LEFT  //数据中心部分
                | snowFlake.getMachineId() << MACHINE_LEFT      //机器标识部分
                | snowFlake.getSequence();                    //序列号部分
    }


    private long getNextMill() {
        long mill = getNewstmp();
        while (mill <= snowFlake.getLastStmp()) {
            mill = getNewstmp();
        }
        return mill;
    }

    private long getNewstmp() {
        return System.currentTimeMillis();
    }
}
