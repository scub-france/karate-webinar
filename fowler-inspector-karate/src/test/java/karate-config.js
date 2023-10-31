function fn() {
    var config = {
        baseUrl: 'https://martinfowler.com'
    };
    if (karate.env == 'docker') {
        karate.configure('driver', {
            type: 'chrome',
            remote: true,
            start: false,
            url: 'http://localhost:9222'
        });
    }
    return config;
}