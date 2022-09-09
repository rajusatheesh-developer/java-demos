package com.demos.java.ds;

import java.util.LinkedList;
import java.util.Stack;

public class StackOfPlates
{

    private final static int STACK_SIZE=3;
    public LinkedList<Stack<Integer>> stackList=new LinkedList<>();

    public void push(Integer i)
    {
        if(stackList.isEmpty()||stackList.getLast().size()==STACK_SIZE)
            stackList.add(new Stack<Integer>());

        stackList.getLast().push(i);

    }

    public static void main(String[] args) {

        StackOfPlates plates=new StackOfPlates();
        plates.push(1);
        plates.push(1);
        plates.push(1);
        plates.push(1);
        plates.push(1);
        plates.push(1);

        System.out.println(plates.stackList);
    }
}
