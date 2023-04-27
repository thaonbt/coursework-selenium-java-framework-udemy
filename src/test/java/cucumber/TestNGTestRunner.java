package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

//Cucumber -> TestNG, JUnit

@CucumberOptions(features="src/test/java/cucumber", glue="UdemyRSA.stepDefinitions",
monochrome=true, plugin= {"html:target/cucumber.html"}, tags= "@Regression")
public class TestNGTestRunner extends AbstractTestNGCucumberTests {

}
