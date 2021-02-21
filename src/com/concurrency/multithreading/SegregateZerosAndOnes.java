package com.concurrency.multithreading;

import java.util.Arrays;

public class SegregateZerosAndOnes
{

	public static void main(String[] args) {
		int[] arr = new int[] { 0, 1, 0, 1, 1, 1, 1, 0, 0, 0, 1 };

		int countOfZeroes = 0;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == 0) {
				countOfZeroes++;
			}
		}

		for (int i = 0; i < countOfZeroes; i++) {
			arr[i] = 0;
		}
		for (int i = countOfZeroes; i < arr.length; i++) {
			arr[i] = 1;
		}
		System.out.println(Arrays.toString(arr));
	}

}

final class Employee
{
	final private String firstName;
	final private String lastName;
	final private double salary;

	public Employee(String firstName, String lastName, double salary) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.salary = salary;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public double getSalary() {
		return salary;
	}
}