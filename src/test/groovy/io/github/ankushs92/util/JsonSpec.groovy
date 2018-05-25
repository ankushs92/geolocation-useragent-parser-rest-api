package io.github.ankushs92.util

import com.fasterxml.jackson.annotation.JsonProperty
import io.github.ankushs92.BaseSpockSpec

/**
 * Created by ankushsharma on 30/01/18.
 */
class JsonSpec extends BaseSpockSpec {


    def "Convert an object to Json String"() {

        given: " Some Person object "
        def person = new Person("Ankush", 24)

        when:
        String json = Json.encode(person)

        then:
        json == '{"name":"Ankush","age":24}'
    }


    def "Pass null json to method.Should throw IllegalArgumentException"() {

        when:
        Json.encode(null)

        then:
        thrown(IllegalArgumentException)
    }


    private static class Person {
        String name
        int age

        Person(
                @JsonProperty("name") String name,
                @JsonProperty("age") int age
        ) {
            this.name = name
            this.age = age
        }
    }
}
