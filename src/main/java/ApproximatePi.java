import akka.actor.*;
import akka.pattern.Patterns;

import java.math.BigInteger;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.TimeUnit;

public class ApproximatePi {
  public static void Execute(long pointCount, int workerCount) throws InterruptedException {
    long startTime = System.currentTimeMillis();
    ActorSystem system = ActorSystem.create("system");


    List<ActorRef> workers = new ArrayList<>();
    for (int i = 0; i < workerCount; i++) {
      workers.add(system.actorOf(Props.create(WorkerActor.class), "worker" + i));
    }

    long workerChunk = pointCount / workerCount;
    System.out.println(workerChunk);
    for (int i = 0; i < workerCount; i++) {
      ActorRef worker = workers.get(i);
      worker.tell(BigInteger.valueOf(workerChunk), ActorRef.noSender());
    }

    List<CompletionStage<Object>> resultFutures = new ArrayList<>();

    for (ActorRef worker : workers) {
      CompletionStage<Object> result = Patterns.ask(worker, BigInteger.valueOf(-1), Duration.ofSeconds(9999));
      resultFutures.add(result);
    }

    List<BigInteger> results = new ArrayList<>();
    resultFutures.forEach(future -> future.thenAccept(result -> {
      results.add((BigInteger) result);
    }));

    do {
      TimeUnit.MILLISECONDS.sleep(10);
    } while (results.size() != workerCount);

    system.terminate();

    long circleCount = 0L;
    for (BigInteger number: results) {
      circleCount += number.longValue();
    }

    float pi = (float)(4 * circleCount) / pointCount;
    long endTime = System.currentTimeMillis();
    double tookTime = (double)(endTime - startTime) / 1000;

    System.out.printf("Took %.2fs with %d threads. Pi: %.6f\n", tookTime, workerCount, pi);
  }
}
