package com.codigo.aplios.toolbox.core.proc;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

// http://winterbe.com/posts/2015/04/07/java8-concurrency-tutorial-thread-executor-examples/

public class SimpleThread {
	public static void main(String[] args) {

		// simpleThreads();
		// simpleExecutors();
		// simpleExecutorStop();
		// simpleCallable();
		// invokeAllCallable();
		SimpleThread.scheludeExecutor();
	}

	/**
	 *
	 */
	public static void scheludeExecutor() {

		ScheduledExecutorService executor = Executors.newScheduledThreadPool(Runtime.getRuntime()
			.availableProcessors());

		Runnable task = () -> {
			try {
				TimeUnit.MINUTES.sleep(11);
				System.out.println("Scheduling: " + System.nanoTime());
			} catch (InterruptedException e) {
				System.err.println("task interrupted");
			}
		};
		executor.scheduleWithFixedDelay(task, 0, 11, TimeUnit.MINUTES);
	}

	public static void invokeAllCallable() {

		ExecutorService executor = Executors.newWorkStealingPool();

		Callable<String> task = () -> {
			try {
				TimeUnit.SECONDS.sleep(6);
				return "123";
			} catch (InterruptedException e) {
				throw new IllegalStateException("task interrupted", e);
			}
		};

		// List<Callable<String>> tasks = Arrays.asList(() -> "Task1", () -> "Task2", () -> "Task3",
		// () -> "Task4", () -> "Task5", () -> "Task6");
		List<Callable<String>> tasks = Arrays.asList(task, task, task);
		try {
			executor.invokeAll(tasks)
				.stream()
				.map(future -> {
					try {
						return future.get();
					} catch (Exception e) {
						throw new IllegalStateException();
					}
				})
				.forEach(System.out::println);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int idx = 0; idx < 1000; idx++)
			System.out.println(idx);

	}

	public static void simpleCallable() {

		Callable<Integer> task = () -> {
			try {
				TimeUnit.SECONDS.sleep(6);
				return 123;
			} catch (InterruptedException e) {
				throw new IllegalStateException("task interrupted", e);
			}
		};

		ExecutorService executor = Executors.newSingleThreadExecutor();
		Future<Integer> future = executor.submit(task);

		System.out.println("future done? " + future.isDone());

		Integer result = 0;
		try {
			result = future.get(6, TimeUnit.SECONDS);
			executor.shutdownNow();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("future done? " + future.isDone());
		System.out.println("Task is running...");

		System.out.print("result: " + result);
	}

	public static void simpleExecutorStop() {

		Runnable task = () -> {
			final String threadName = Thread.currentThread()
				.getName();
			System.out.println("Thread name is: " + threadName);
			try {
				TimeUnit.MILLISECONDS.sleep(100L);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		};

		ExecutorService executor = Executors.newSingleThreadExecutor();
		executor.submit(task);

		System.out.println("attempt to shutdown executor");
		executor.shutdown();
		try {
			executor.awaitTermination(5, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			System.err.println("tasks interrupted");
		} finally {
			if (!executor.isTerminated()) {
				System.err.println("cancel non-finished tasks");
				executor.shutdownNow();
				System.out.println("shutdown finished");
			}
		}
	}

	public static void simpleExecutors() {

		final Runnable task = () -> {
			final String threadName = Thread.currentThread()
				.getName();
			System.out.println("Thread name is: " + threadName);
			try {
				TimeUnit.SECONDS.sleep(4);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		};

		final ExecutorService executor = Executors.newSingleThreadExecutor();
		executor.submit(task);
	}

	public static void simpleThreads() {

		Runnable task = () -> {
			final String threadName = Thread.currentThread()
				.getName();
			System.out.println("Thread name is: " + threadName);
			try {
				TimeUnit.SECONDS.sleep(4);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		};
		// task.run();
		Thread th1 = new Thread(task);
		th1.start();

		Thread th2 = new Thread(task);
		th2.start();

		Thread th3 = new Thread(task);
		th3.start();

		Thread th4 = new Thread(task);
		th4.start();

		Thread th5 = new Thread(task);
		th5.start();

		Thread th6 = new Thread(task);
		th6.start();

		Thread th7 = new Thread(task);
		th7.start();

		Thread th8 = new Thread(task);
		th8.start();

		Thread th9 = new Thread(task);
		th9.start();

		Thread th10 = new Thread(task);
		th10.start();

		System.out.println("Done!");
	}
}
