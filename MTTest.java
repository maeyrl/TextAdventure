//MultiThreaded Extreme Stress Tester
//Creates args[0] * 16 Rooms
//As well as args[0] * 10 RoomManagers
//Also makes 32% Skeleton/per room

public class MTTest{
	static double skC = 0;
	static double rooms = 0;
	
	public static void main(String args[]) {
		long start = System.currentTimeMillis();
		try {
			System.out.println("Generating " + (Integer.valueOf(args[0]))*16 + " rooms.");
			for (i = 3; i>= 0; i--) {
				System.out.println(i);
				Thread.sleep(1000);
			}
			
		} catch (Exception e) {}
		
		for(int i = 1; i<=Integer.valueOf(args[0]); i++) {
			
			Thread t = new Thread(new Runnable() {
				
				@Override //<- my compiler will bitch about this
				public void run() {
					for (int i = 0; i < 10; i++) {
						running();
						try {
							Thread.sleep(1);
						} catch (Exception e) {}
					}
				}
			});
			
			t.start();
			
			try {
				t.join();
			} catch (Exception e) {}
			
		}
		System.out.println("Total Skeletons: " + (int) skC + "//" + (int) rooms);
		System.out.println("Total RoomManagers: " + (int) rooms/16);
		System.out.println("Total Rooms " + (int) rooms);
		float d = (float) ((skC / rooms)*100);
		System.out.printf("Percentage Chance: %6.5f", d);
		
		long end = System.currentTimeMillis();
		System.out.println();
		System.out.println();
        
        System.out.println("Time taken: " + (end - start) + "ms");
	}
	
	public static void running() {
		RoomManager rm = new RoomManager();
		for(int i = 1; i<=4; i++) {
			for(int j = 1; j<=4; j++) {
				System.out.print(""
								 + rm.getRoomAt(i,j).getInfo().charAt(0)
								 + "" 
								 + rm.getRoomAt(i, j).getInfo().charAt(1)
								 + "       "
								 );
				if(("" + rm.getRoomAt(i, j).getInfo().charAt(1)).equals("S")) {
					skC++;
				}
			}
			System.out.println();
			
		}
		System.out.println("=============================================");
		rooms += 16;
	}
}
