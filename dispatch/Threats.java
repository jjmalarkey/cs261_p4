package dispatch;

import java.util.Map;
import java.util.HashMap;

import dispatch.*;

public class Threats {
	private Map<String, Map<String, Emergency.Threat>> keywordCategory; //for resolving type of emergency, and severity
	private Map<String, Emergency.Responder> responseCategory;

	public Emergency categorizeCall(String call) {
		String category;
		Emergency response = new Emergency(call);
		Emergency.Threat priority;
		Emergency.Responder responder;
		for(Map.Entry<String, Map<String, Emergency.Threat>> entry : keywordCategory.entrySet()) {
			String k = entry.getKey();
			if(call.contains(k)) {
				response.responder = responseCategory.get(k); //all keywords map to a response type
				boolean foundCategory = false;
				Map<String, Emergency.Threat> v = entry.getValue();
				if(v.containsKey("all")) {
					response.priority = v.get("all");
				} else {
				for (Map.Entry<String, Emergency.Threat> inEntry : v.entrySet()) {
					String type = inEntry.getKey();
					if(call.contains(type)) {
						response.priority = inEntry.getValue();
						foundCategory = true;
					}
				}
				if(foundCategory == false)
					response.priority = v.get("else");
				}
			}
		}
		return response;
	}

	public Threats(){
		keywordCategory = new HashMap<String, Map<String, Emergency.Threat>>(); //for resolving type of emergency
		Map<String, Emergency.Threat> veh = new HashMap<String, Emergency.Threat>();
		Map<String, Emergency.Threat> env = new HashMap<String, Emergency.Threat>();
		Map<String, Emergency.Threat> fac = new HashMap<String, Emergency.Threat>();
		Map<String, Emergency.Threat> med = new HashMap<String, Emergency.Threat>();
		responseCategory = new HashMap<String, Emergency.Responder>();
	//initialize lookup table for vehicle
		veh.put("accident", Emergency.Threat.HIGH);
		veh.put("else", Emergency.Threat.LOW);
		keywordCategory.put("tire", veh);
		keywordCategory.put("car", veh);
		keywordCategory.put("vehicle", veh);
		responseCategory.put("vehicle", Emergency.Responder.VEHICLE);
		responseCategory.put("car", Emergency.Responder.VEHICLE);
		responseCategory.put("tire", Emergency.Responder.VEHICLE);
	//initialize lookup table for environment
		env.put("all", Emergency.Threat.HIGH);
		keywordCategory.put("dome", env);
		keywordCategory.put("meteor", env);
		keywordCategory.put("quake", env);
		responseCategory.put("dome", Emergency.Responder.ENVIRONMENT);
		responseCategory.put("meteor", Emergency.Responder.ENVIRONMENT);
		responseCategory.put("quake", Emergency.Responder.ENVIRONMENT);
	//initialize lookup table for facility
		fac.put("all", Emergency.Threat.LOW);
		keywordCategory.put("pipes", fac);
		keywordCategory.put("air", fac);
		keywordCategory.put("facility", fac);
		keywordCategory.put("lock", fac);
		keywordCategory.put("power", fac);
		keywordCategory.put("leak", fac);
		keywordCategory.put("window", fac);
		responseCategory.put("pipes", Emergency.Responder.FACILITY);
		responseCategory.put("air", Emergency.Responder.FACILITY);
		responseCategory.put("facility", Emergency.Responder.FACILITY);
		responseCategory.put("lock", Emergency.Responder.FACILITY);
		responseCategory.put("power", Emergency.Responder.FACILITY);
		responseCategory.put("leak", Emergency.Responder.FACILITY);
		responseCategory.put("window", Emergency.Responder.FACILITY);
	//initialize lookup table for medical
		med.put("else", Emergency.Threat.MEDIUM);
		med.put("seizure", Emergency.Threat.HIGH);
		med.put("breathing", Emergency.Threat.HIGH);
		keywordCategory.put("daughter", med);
		keywordCategory.put("son", med);
		keywordCategory.put("spouse", med);
		responseCategory.put("daughter", Emergency.Responder.MEDICAL);
		responseCategory.put("son", Emergency.Responder.MEDICAL);
		responseCategory.put("spouse", Emergency.Responder.MEDICAL);
	}
}
