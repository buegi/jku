package algodat1.ue05;

import java.util.*;

public class Array {
		    public static void main(String[] args) {
	        
	        int j,k;
	        int m = 5;
	        int n = 3;
	        int[] am = {2, 12, 22, 43, 78, 0, 0, 0};
	        int[] an = {1, 31, 112};
	        
	        for (int i = 0; i < n; i++){
	            j = 0;
	            while (j<m && an[i]>am[j]){
	                j++;
	            }
	            
	            k = m;
	            while (k > j) {
	                am[k] = am[k-1];
	                k--;
	            }
	        
	            am[j] = an[i];
	            m++;
	        }
	    System.out.println(Arrays.toString(am));
	    System.out.println(Arrays.toString(an));
	    }
	}