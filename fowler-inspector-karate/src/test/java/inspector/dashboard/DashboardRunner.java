package scrapper.dashboard;

import com.intuit.karate.junit5.Karate;

class DashboardRunner {
    
    @Karate.Test
    Karate testUsers() {
        return Karate.run("main_test").relativeTo(getClass());
    }    

}
