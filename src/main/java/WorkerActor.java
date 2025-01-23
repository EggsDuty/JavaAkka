import akka.actor.AbstractActor;

import java.math.BigInteger;

public class WorkerActor extends AbstractActor {

    BigInteger count = BigInteger.ZERO;

    @Override
    public Receive createReceive() {
        return receiveBuilder()
            .match(PointArray.class, message -> {
                if (message.last) {
                    getSender().tell(count, getSelf());
                    getContext().stop(getSelf());
                } else {

                    for (int i = 0; i < message.size(); i++) {
                        Point point = message.get(i);
                        float x = point.x;
                        float y = point.y;

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