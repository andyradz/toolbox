package com.codigo.aplios.toolbox.core.domain;

// package domain;
//
// import javax.swing.event.EventListenerList;
//
// import com.google.java.contract.Requires;
//
// public class CarBean {
//
// //http://www.javaworld.com/article/2074481/java-concurrency/java-101--understanding-java-threads--part-4---thread-groups--volatility--and-threa.html
// //http://www.baeldung.com/java-timer-and-timertask
//
// public static void main(String[] args) {
// System.out.println(Thread.currentThread());
// ThreadGroup tgWorkers = new ThreadGroup ("workers");
//
// final Thread thread1 = new Thread(tgWorkers, "Thread-1") {
//
// @Override
// public void run() {
//
// SpeedListener lis1 = new SpeedListener() {
// @Override
// public void speedExceeded(SpeedEvent e) {
//
// if (e.getCurrentSpeed() > e.getMaxSpeed()) {
// System.out.println(Thread.currentThread() + "::Alert! You have exceeded " + (e.getCurrentSpeed() - e
// .getMaxSpeed() + " MPH!"));
// }
// }
//
// @Override
// public void speedGoneBelow(SpeedEvent e) {
//
// if (e.getCurrentSpeed() < e.getMinSpeed()) {
// System.out.println(Thread.currentThread() + "::Uhm... you are driving " + e.getCurrentSpeed()
// + " MPH. Speed up!");
// }
// }
// };
// CarBean myCar = new CarBean(60, 40, 10);
// myCar.addSpeedListener(lis1);
// myCar.speedUp(60); // fires SpeedEvent
// myCar.delSpeedListener(lis1);
// }
// };
//
// final Thread thread2 = new Thread(tgWorkers, "Thread-2") {
// @Override
// public void run() {
//
// SpeedListener lis2 = new SpeedListener() {
// @Override
// public void speedExceeded(SpeedEvent e) {
//
// if (e.getCurrentSpeed() > e.getMaxSpeed()) {
// System.out.println(Thread.currentThread() + "::Alert! You have exceeded " + (e.getCurrentSpeed() - e
// .getMaxSpeed() + " MPH!"));
// }
// }
//
// @Override
// public void speedGoneBelow(SpeedEvent e) {
//
// if (e.getCurrentSpeed() < e.getMinSpeed()) {
// System.out.println(Thread.currentThread() + "::Uhm... you are driving " + e.getCurrentSpeed()
// + " MPH. Speed up!");
// }
// }
// };
// CarBean myCar = new CarBean(60, 40, 10);
// myCar.addSpeedListener(lis2);
// myCar.speedUp(150); // fires SpeedEvent
// myCar.delSpeedListener(lis2);
// }
// };
//
// final Thread thread3 = new Thread(tgWorkers, "Thread-3") {
// @Override
// public void run() {
//
// SpeedListener lis2 = new SpeedListener() {
// @Override
// public void speedExceeded(SpeedEvent e) {
//
// if (e.getCurrentSpeed() > e.getMaxSpeed()) {
// System.out.println(Thread.currentThread() + "::Alert! You have exceeded " + (e.getCurrentSpeed() - e
// .getMaxSpeed() + " MPH!"));
// }
// }
//
// @Override
// public void speedGoneBelow(SpeedEvent e) {
//
// if (e.getCurrentSpeed() < e.getMinSpeed()) {
// System.out.println(Thread.currentThread() + "::Uhm... you are driving " + e.getCurrentSpeed()
// + " MPH. Speed up!");
// }
// }
// };
// CarBean myCar = new CarBean(60, 40, 10);
// myCar.addSpeedListener(lis2);
// myCar.slowDown(150); // fires SpeedEvent
// myCar.delSpeedListener(lis2);
// }
// };
//
// final Thread thread4 = new Thread(tgWorkers, "Thread-4") {
// @Override
// public void run() {
//
// SpeedListener lis2 = new SpeedListener() {
// @Override
// public void speedExceeded(SpeedEvent e) {
//
// if (e.getCurrentSpeed() > e.getMaxSpeed()) {
// System.out.println(Thread.currentThread() + "::Alert! You have exceeded " + (e.getCurrentSpeed() - e
// .getMaxSpeed() + " MPH!"));
// }
// }
//
// @Override
// public void speedGoneBelow(SpeedEvent e) {
//
// if (e.getCurrentSpeed() < e.getMinSpeed()) {
// System.out.println(Thread.currentThread() + "::Uhm... you are driving " + e.getCurrentSpeed()
// + " MPH. Speed up!");
// }
// }
// };
// CarBean myCar = new CarBean(60, 40, 10);
// myCar.addSpeedListener(lis2);
// myCar.slowDown(100); // fires SpeedEvent
// myCar.delSpeedListener(lis2);
// }
// };
//
// thread1.setPriority(Thread.MAX_PRIORITY);
// thread1.start();
// thread2.start();
// thread3.start();
// thread4.setPriority(Thread.MIN_PRIORITY);
// thread4.start();
//
// // System.out.println(myCar);
// }
//
// private int maxSpeed;
// private int minSpeed;
// private int currentSpeed;
//
// private EventListenerList speedListenerList = new EventListenerList();
//
// @Requires({
// "min < max and min <= cur and max >= cur" })
// private CarBean(int max, int min, int cur) {
// this.maxSpeed = max;
// this.minSpeed = min;
// this.currentSpeed = cur;
// }
//
// // Register an event listener
// public void addSpeedListener(SpeedListener listener) {
//
// speedListenerList.add(SpeedListener.class, listener);
// }
//
// // Unregister an event listener
// public void delSpeedListener(SpeedListener listener) {
//
// speedListenerList.remove(SpeedListener.class, listener);
// }
//
// public void speedUp(int increment) {
//
// this.currentSpeed += increment;
// if (this.currentSpeed > this.maxSpeed) {
// // fire SpeedEvent
// // processSpeedEvent(new SpeedEvent(this, this.maxSpeed, this.minSpeed,
// // this.currentSpeed));
// // When SpeedEvent does not extend java.util.EventObject
// fireSpeedEvent(new SpeedEvent(this, this.maxSpeed, this.minSpeed, this.currentSpeed));
// }
// }
//
// /**
// * @param increment
// */
// public void slowDown(int increment) {
//
// this.currentSpeed -= increment;
// if (this.currentSpeed < this.minSpeed) {
// // fire SpeedEvent
// // processSpeedEvent(new SpeedEvent(this, this.maxSpeed, this.minSpeed,
// // this.currentSpeed));
// // When SpeedEvent does not extend java.util.EventObject
// fireSpeedEvent(new SpeedEvent(this, this.maxSpeed, this.minSpeed, this.currentSpeed));
// }
// }
//
// @Override
// public String toString() {
//
// return "cur:" + this.currentSpeed + " " + "min:" + this.minSpeed + " " + "max:" + this.maxSpeed;
// }
//
// private void fireSpeedEvent(SpeedEvent speedEvent) {
//
// /*
// * ArrayList<SpeedListener> tempSpeedListenerList;
// *
// * synchronized (this) {
// * if (speedListenerList.getListenerCount(SpeedListener.class) == 0) {
// * return;
// * }
// * tempSpeedListenerList = ArrayList.class.cast(speedListenerList.clone());
// * }
// */
// SpeedListener[] listenerList = this.speedListenerList.getListeners(SpeedListener.class);
//
// for (SpeedListener listener : listenerList) {
// listener.speedExceeded(speedEvent);
// listener.speedGoneBelow(speedEvent);
// }
// }
//
// }
