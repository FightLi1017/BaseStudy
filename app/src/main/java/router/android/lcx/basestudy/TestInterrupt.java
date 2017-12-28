package router.android.lcx.basestudy;

import java.math.BigInteger;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author：lichenxi
 * @date 2017/12/14 15
 * email：525603977@qq.com
 * Fighting
 */
public class TestInterrupt {
    class PrimeProducer extends Thread{
       private final BlockingQueue<BigInteger> mBlockingQueue;

        public PrimeProducer(BlockingQueue<BigInteger> bigIntegers) {
            mBlockingQueue = bigIntegers;
        }

        @Override
        public void run() {
            try{
                BigInteger p=BigInteger.ONE;
                while (!Thread.currentThread().isInterrupted()){
                    mBlockingQueue.put(p=p.nextProbablePrime());
                }
            }catch (InterruptedException con){
                Thread.currentThread().interrupt();
                con.printStackTrace();
                System.out.println("线程中断了");
            }
        }
        public void cancel(){
            interrupt();
        }
    }
    public static void main(String[] args) {

    }
}
