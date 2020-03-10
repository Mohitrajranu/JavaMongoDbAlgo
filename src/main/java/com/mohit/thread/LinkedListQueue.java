package com.mohit.thread;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;



public class LinkedListQueue {

	public static List<String> fetchQueueElement(Queue<String> queue) {
        List<String> emailTasks = new ArrayList<>();
        try {
            if (queue == null || queue.isEmpty()) {
                return emailTasks;
            }
            int size = queue.size();
            
            for (int i = 0; i < size; i++) {
                String email = queue.poll();
                emailTasks.add(email);
              System.out.println("Email found is "+email +" queue size "+queue.size());
            }

        } catch (Exception e) {
            System.err.println("Error in fetchQueueElement " + e.getMessage());
        }
        return emailTasks;
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		  Queue<String> queue = new LinkedList<String>();
		  queue.add("mohit.raj@bizlem.io");
		  queue.add("siva@bizlem.io");
		  queue.add("tejal@bizlem.io");
		  while (true) {
              List<String> tasks = fetchQueueElement(queue);
              System.out.println("tasks size "+queue.size());
              if (tasks.isEmpty()) {
                  break;
              }
              
		  }
	}

}
