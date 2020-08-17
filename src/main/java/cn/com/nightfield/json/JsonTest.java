package cn.com.nightfield.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonTest {
	public static void main(String[] args) throws JsonProcessingException {
		String json = "{\"id\":\"68e18b01b34e4754b1eac9f92305d1ee\", " +
				   "\"test\":[\"1\", \"2\"]," +
		           "\"vip\":\"10.240.56.150\"}";
//				   "svrtypes":["mpisvr"],
//				   "supportgroups":["Monitoring Operations"],
//				   "supportgroupMaillist":["csg-mon@cisco.com"],
//				   "name":"mctapi-vs-443",
//				   "port":null,
//				   "externalId":null,
//				   "description":null,
//				   "loadBalancerPri":"dfw01-wxp00-lbace05a",
//				   "loadBalancerSec":"dfw01-wxp00-lbace05b",
//				   "serverList":[{"ip":"10.240.56.122","hostname":"mttx1mpi003.webex.com"},{"ip":"10.240.56.35","hostname":"mttx1mpi001.webex.com"},{"ip":"10.240.56.123","hostname":"mttx1mpi004.webex.com"},{"ip":"10.240.56.36","hostname":"mttx1mpi002.webex.com"}],
//				   "poolList":[],
//				   "dnsList":["mctapi.webex.com",
//				   "global-mctapi.webex.com"],
//				   "decommission":false"
		ObjectMapper mapper = new ObjectMapper();
		JsonNode jsonNode = mapper.readTree(json);
		JsonNode testNode = jsonNode.get("test");
		if (testNode.isArray()) {
			for (JsonNode elements : testNode) {
				System.out.println(elements);
			}
		}
		System.out.println(testNode.toString());
	}
}
