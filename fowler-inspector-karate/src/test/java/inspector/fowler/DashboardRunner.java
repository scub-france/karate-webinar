package inspector.fowler;

import com.intuit.karate.junit5.Karate;

class DashboardRunner {
    
    @Karate.Test
    Karate testUsers() {
        return Karate.run("fowler_inspector").relativeTo(getClass());
    }    

}
