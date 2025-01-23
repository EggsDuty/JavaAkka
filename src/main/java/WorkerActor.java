import akka.actor.AbstractActor;

import java.math.BigInteger;
import java.util.concurrent.ThreadLocalRandom;

public class WorkerActor extends AbstractActor {

    BigInteger count = BigInteger.ZERO;

    @Override
    public Receive createReceive() {
        return receiveBuilder()
            .match(BigInteger.class, message -> {
                if (message.longValue() == -1) {
                    getSender().tell(count, getSelf());
                    getContext().stop(getSelf());
                } else {
                    for (long i = 0L; i < message.longValue(); i++) {
                        float x = (float)(-1 + ThreadLocalRandom.current().nextDouble() * 2);
                        float y = (float)(-1 + ThreadLocalRandom.current().nextDouble() * 2);

                        double dist = Math.pow(x, 2)+Math.pow(y, 2);
                        if (dist <= 1) {
                            count = count.add(BigInteger.ONE);
                        }
                    }
                }
            })
            .build();
    }
}