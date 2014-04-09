package hello;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;

@WebService
public class CalculatriceFunctions {
	
	@WebMethod public double plus(double r, double s){
		return r + s;
	}
	
	@WebMethod public double moins(double r, double s){
		return r - s;
	}
	
	@WebMethod public double fois(double r, double s){
		return r * s;
	}
	
	@WebMethod public double division(double r, double s){
		return r / s;
	}
	
	public static void main(String[] args){
		Endpoint.publish("http://localhost:8080/WebServiceExample/calculatricefunctions", new CalculatriceFunctions());
	}
}

