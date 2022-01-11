package com.sunil.webflow.demo;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class MonoFluxTest {
	
	/*
	 * Reactive Framework Steps
	 * 1. subscribe()  (consumer --> publisher)
	 * 2. subscription() (publisher --> consumer)
	 * 3. request(n)  --> Request n events  (consumer --> publisher)
	 * 4. onNext(data) --> (publisher --> consumer)
	 * 5. onComplete() or onError() --> When all events are emitted to consumer (publisher to consumer)
	 */
	@Test
	public void testMono() {
		
		// log is to see the detailed output on Mono.just
		Mono<?> monoString = Mono.just("Sunil Kothireddy")
				// to show onError() invocation
				//.then(Mono.error(new RuntimeException("Manual exception")))
				.log();
		//Subscribe to the Publisher  and handle the exception
		monoString.subscribe(System.out::println, (e)->System.out.println(e.getMessage()));
	
		/*
		 * 	20:32:21.314 [main] INFO reactor.Mono.Just.1 - | onSubscribe([Synchronous Fuseable] Operators.ScalarSubscription)
			20:32:21.316 [main] INFO reactor.Mono.Just.1 - | request(unbounded)
			20:32:21.317 [main] INFO reactor.Mono.Just.1 - | onNext(Sunil Kothireddy)
			Sunil Kothireddy
			20:32:21.318 [main] INFO reactor.Mono.Just.1 - | onComplete()
		 */
		
		/*
		 * To show onError() invocation
		 * 20:38:08.715 [main] DEBUG reactor.util.Loggers - Using Slf4j logging framework
			20:38:08.734 [main] INFO reactor.Mono.IgnoreThen.1 - onSubscribe(MonoIgnoreThen.ThenIgnoreMain)
			20:38:08.737 [main] INFO reactor.Mono.IgnoreThen.1 - request(unbounded)
			20:38:08.742 [main] ERROR reactor.Mono.IgnoreThen.1 - onError(java.lang.RuntimeException: Manual exception)
			20:38:08.744 [main] ERROR reactor.Mono.IgnoreThen.1 - 
			java.lang.RuntimeException: Manual exception
		 */
		}
		@Test
		public void testFlux() {
			Flux<String> fluxString = Flux.just("Sunil","Kothireddy","Little Elm")
					.concatWith(Flux.error(new RuntimeException("Manual exception")))
					.log();
			
			fluxString.subscribe(System.out::println, (e)->System.out.println(e.getMessage()));
		}
		/*
		 * 20:43:19.090 [main] INFO reactor.Flux.Array.1 - | onSubscribe([Synchronous Fuseable] FluxArray.ArraySubscription)
			20:43:19.092 [main] INFO reactor.Flux.Array.1 - | request(unbounded)
			20:43:19.093 [main] INFO reactor.Flux.Array.1 - | onNext(Sunil)
			Sunil
			20:43:19.093 [main] INFO reactor.Flux.Array.1 - | onNext(Kothireddy)
			Kothireddy
			20:43:19.093 [main] INFO reactor.Flux.Array.1 - | onNext(Little Elm)
			Little Elm
			20:43:19.093 [main] INFO reactor.Flux.Array.1 - | onComplete()
		 */
		
		/*
		 * 20:49:03.212 [main] DEBUG reactor.util.Loggers - Using Slf4j logging framework
			20:49:03.229 [main] INFO reactor.Flux.ConcatArray.1 - onSubscribe(FluxConcatArray.ConcatArraySubscriber)
			20:49:03.231 [main] INFO reactor.Flux.ConcatArray.1 - request(unbounded)
			20:49:03.232 [main] INFO reactor.Flux.ConcatArray.1 - onNext(Sunil)
			Sunil
			20:49:03.232 [main] INFO reactor.Flux.ConcatArray.1 - onNext(Kothireddy)
			Kothireddy
			20:49:03.232 [main] INFO reactor.Flux.ConcatArray.1 - onNext(Little Elm)
			Little Elm
			20:49:03.233 [main] ERROR reactor.Flux.ConcatArray.1 - onError(java.lang.RuntimeException: Manual exception)
			20:49:03.234 [main] ERROR reactor.Flux.ConcatArray.1 - 
			java.lang.RuntimeException: Manual exception
		 */

}
