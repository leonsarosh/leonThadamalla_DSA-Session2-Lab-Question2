import java.util.Scanner;

public class TeamSelection {
	
	static int count1=0, playersPerTeam=0;
	
	void callQuickSort(int[] arr, int firstIndex, int lastIndex) {
		if (firstIndex<lastIndex) {
			int p_index=partition(arr,firstIndex,lastIndex);
			callQuickSort(arr,firstIndex,p_index-1);
			callQuickSort(arr,p_index+1,lastIndex);
		}
	}
	
	int partition(int[] arr, int firstIndex, int lastIndex) {
		int pivot=arr[lastIndex];
		int i=(firstIndex-1);
		for(int j=firstIndex;j<lastIndex;j++) {
			if(arr[j]<pivot) {
				i++;
				swap(arr,i,j);
			}
		}
		swap(arr,i+1,lastIndex);	
		return (i+1);
	}
	
	void swap(int arr[],int i, int j) {
		int temp=arr[i];
		arr[i]=arr[j];
		arr[j]=temp;
	}
	
	void arrayPrintCall(int arr[]) {
		System.out.print("{");
		for(int i=0;i<arr.length;i++) {
			System.out.print(" "+arr[i]+" ");
		}
		System.out.println("}");
	}
	
	void callSortDescending (int[] arr,int firstIndex,int lastIndex) {
		callQuickSort(arr,firstIndex,lastIndex);
		int i=firstIndex, j=lastIndex;
		if (arr.length%2==0) {
			for (i=firstIndex;i<=lastIndex/2;i++) {
				swap(arr,i,j);
				j--;
			}
		} else {
			for (i=firstIndex;i<=(lastIndex/2)-1;i++) {
				swap(arr,i,j);
				j--;
			}
		}
	}
	
	void callSortAscending (int[] arr,int firstIndex,int lastIndex) {
		callQuickSort(arr,firstIndex,lastIndex);	
	}

	int teamSelectionStep(int[] arr) {
		int count2=0;
		for(int i=0;i<arr.length;i++) {
			if(arr[i]>0) {
				count2++;
			}
		}
		if(count2<playersPerTeam) {
			return count1;
		} else {
			int arr2[]=new int[playersPerTeam];
			for(int k=0;k<playersPerTeam;k++) {
				int w=0;
				int choice=checkNext(arr,w);
				if(choice==-1) {
					return count1;
				} else {
					arr2[k]=choice;
					if (k!=0) {
						for(int x=0;x<k;x++) {
							if(choice==arr2[x]) {
								choice=checkNext(arr,arr2[x]+1);
								if(choice==-1) {
									return count1;
								}
							} 
						}
						arr[choice]=arr[choice]-1;
					} else {
							arr[choice]=arr[choice]-1;
					}
				}
				System.out.println("Player "+(k+1)+" of team "+(count1+1)+" selected");
				arrayPrintCall(arr);
			}
			count1++;
			System.out.println("Team number formed: "+count1);
			arrayPrintCall(arr);
			return teamSelectionStep(arr);
		}
	}
	
	int checkNext(int[] arr, int w) {
		if(w==arr.length-1 && arr[w]==0) {
			return -1;
		} else if(w==arr.length-1 && arr[w]>0) {
			return w;
		} else if (arr[w]>0 && arr[w]>arr[w+1]) {
			return w;
		} else {
			return checkNext(arr,w+1);
		}
	}
	
	public static void main(String[] args) {
		
		Scanner sc=new Scanner(System.in);
		
		System.out.print("Please enter the number of countries participating: ");
		int numberOfCountries=sc.nextInt();
		System.out.println();
		
		int arrNumberOfPlayers[]=new int[numberOfCountries];
		
		for(int i=0;i<arrNumberOfPlayers.length;i++) {
			
			System.out.print("Please enter the number of players for country "+(i+1)+" : ");
			arrNumberOfPlayers[i]=sc.nextInt();
			System.out.println();
		}
		
		System.out.print("Please enter the number of players per team to be formed: ");
		playersPerTeam=sc.nextInt();
		System.out.println();
				
		TeamSelection obj1=new TeamSelection();
		obj1.arrayPrintCall(arrNumberOfPlayers);
		
		obj1.callSortAscending(arrNumberOfPlayers,0,arrNumberOfPlayers.length-1);
		obj1.arrayPrintCall(arrNumberOfPlayers);
//		obj1.callSortDescending(arrNumberOfPlayers,0,arrNumberOfPlayers.length-1);
//		obj1.arrayPrintCall(arrNumberOfPlayers);
		
		if(playersPerTeam==numberOfCountries) {
			System.out.println("The number of teams that can be formed is: "+arrNumberOfPlayers[arrNumberOfPlayers.length-1]);
		} else if (playersPerTeam<numberOfCountries) {
			int numberOfTeamsFormed=obj1.teamSelectionStep(arrNumberOfPlayers);
			System.out.println("Number of teams that can be formed is: "+numberOfTeamsFormed);
			count1=0;
		} else {
			System.out.println("Since the number of players per team is greater than the number of countries and players from the same country cannot be selected for the same team, number of teams formed is: 0");
		}
		sc.close();
	}
}
