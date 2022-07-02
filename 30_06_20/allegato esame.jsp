<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*"%>
<%!
	String title = "NetCalc";
	
	protected int[] parseIp(String ip){
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
	
	protected int[] calcolaNetmask(int n){
		int shft = 0xffffffff<<(32-n);
		int oct1 = ((byte) ((shft&0xff000000)>>24)) & 0xff;
		int oct2 = ((byte) ((shft&0x00ff0000)>>16)) & 0xff;
		int oct3 = ((byte) ((shft&0x0000ff00)>>8)) & 0xff;
		int oct4 = ((byte) (shft&0x000000ff)) & 0xff;
		int[] netmask = new int[] {oct1,oct2,oct3,oct4};
		return netmask;
	}
    protected int[] calcolaNetid(int[] ip, int[] netmask){
    	int[] netid = new int[4];
    	for (int i=0; i<4; i++)
    		netid[i] = ip[i] & netmask[i];
    	return(netid);}
    protected int calcolaNumHost(int n){
    	if (n<32)
    		return((int)Math.pow(2,32-n)-2);
    	else
    		return 1;}
%>
<%	
	String url = request.getRequestURL().toString();
	String ip = request.getParameter("ip");
	String n=request.getParameter("n");
 %>
 <!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>
<%=title%>
</title>
</head>
<body>
	<h1><%=title%></h1>
	<form method=POST action=<%=url%> >
		Indirizzo ip: 
		 <input type=text name="ip" value="<%=(ip != null)?ip:"" %>" />
		 / <input type=text name="n" value="<%=(n != null)?n:"" %>" /> 
		 <input type=submit name="go" value="calcola"></input>
	</form><br><% 
	if (ip != null && n!=null){
		int[] intip = parseIp(ip);
		if (intip == null){
			%> Il formato dell'indirizzo IP non risulta corretto <%
		}
		try{
			int intn = Integer.parseInt(n);
			if (intn < 0 || intn > 32){
				%>Verificare che il valore della netmask sia compreso tra 0 e 32<%
				return;
			}; 			
			int[] netmask = calcolaNetmask(intn);
			int[] netid = calcolaNetid(intip,netmask);
			int numhost = calcolaNumHost(intn);
			%><p>
			Netmask:
			<%= netmask[0]%>.<%= netmask[1]%>.<%= netmask[2]%>.<%= netmask[3]%><br>
			Network:
			<%= netid[0]%>.<%= netid[1]%>.<%= netid[2]%>.<%= netid[3]%>/<%=intn%><br>
			Numero di host:
			<%= numhost%><br>
			</p><%				
		} catch (Exception ee){
			%>Formato non valido! L'indirizzo ip deve essere composto da quattro numeri interi separati da . e la netmask in formato CIDR deve essere un numero intero<%
		}
	} else {
		%>Occorre inserire sia l'indirizzo IP sia la netmask!<%
	}%>
</body>
</html>