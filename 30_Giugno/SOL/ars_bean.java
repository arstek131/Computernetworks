package esame.org;

public class FABIO_ROMAGNOLO_BEAN {
	
	public int[] parseIp(String ip){
		String[] ottetti = ip.split("\\.");
		int[] intip = new int[4];
		if (ottetti.length !=4)
			return null;
		for (int i =0; i<4; i++){
			intip[i] = Integer.parseInt(ottetti[i]);
			if(intip[i]<0 || intip[i] > 255){
				return null;
			}
		}
		return intip;
	}
	
	public int[] calcolaNetmask(int n){
		int shft = 0xffffffff<<(32-n);
		int oct1 = ((byte) ((shft&0xff000000)>>24)) & 0xff;
		int oct2 = ((byte) ((shft&0x00ff0000)>>16)) & 0xff;
		int oct3 = ((byte) ((shft&0x0000ff00)>>8)) & 0xff;
		int oct4 = ((byte) (shft&0x000000ff)) & 0xff;
		int[] netmask = new int[] {oct1,oct2,oct3,oct4};
		return netmask;
	}
	
	public int[] calcolaNetid(int[] ip, int[] netmask){
    	int[] netid = new int[4];
    	for (int i=0; i<4; i++)
    		netid[i] = ip[i] & netmask[i];
    	return(netid);}
    
    public int calcolaNumHost(int n){
    	if (n<32)
    		return((int)Math.pow(2,32-n)-2);
    	else
    		return 1;}
    
   

}