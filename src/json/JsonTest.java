package json;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JsonTest {
	public static void main(String[] args) {
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
		JSONObject j = JSONObject.fromObject(json);
		JSONArray ja = j.getJSONArray("test");
		System.out.println(ja.get(0));
		Object[] arr = ja.toArray();
		System.out.println(ja.toString());
	}
}
