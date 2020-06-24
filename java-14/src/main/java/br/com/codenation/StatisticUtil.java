package br.com.codenation;

import java.util.Arrays;

public class StatisticUtil {

	public static int average(int[] elements) {
		int sum = 0;
		for (int i = 0; i < elements.length; i++) {
			sum += elements[i];
		}
		return (sum / elements.length);
	}

	public static int mode(int[] elements) {

		int[] mode = new int[2];
		mode[0] = 0;
		Boolean noRepeatedElementes = true;
		for (int i = 0; i < elements.length; i++) {
			int count = 0;
			for (int j = 0; j < elements.length; j++) {
				if (elements[i] == elements[j]) {
					count++;
				}
				if (count > mode[0]) {
					mode[0] = count;
					mode[1] = elements[i];
					noRepeatedElementes = false;
				}
			}
		}
		if (noRepeatedElementes) {
			return elements[0];
		}
		return mode[1];
	}

	public static int median(int[] elements) {
		//bubble sort
		Arrays.sort(elements);
		int aux=0;
		for(int i=0;i<elements.length;i++){
			for (int j=1;j<elements.length-1;j++){
				if(elements[j]<elements[j+1]){
					aux=elements[j];
					elements[j] = elements[j+1];
					elements[j+1] = aux;
				}
			}
		}
		if(elements.length%2==1){
			return elements[(elements.length/2)+1];
		}
		
		return ( (elements[(elements.length/2)]+ elements[(elements.length/2)+1])/2);
	}
}