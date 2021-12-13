import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import org.openqa.selenium.By as By
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariables
import net.bytebuddy.asm.Advice.Exit as Exit
import groovy.json.JsonSlurper as JsonSlurper
import com.kms.katalon.core.testobject.SelectorMethod as SelectorMethod
import internal.GlobalVariable as GlobalVariable

//log request
responseLog = WS.sendRequest(findTestObject('logInRequest'))

//verify if the respont is 200 for good
WS.verifyResponseStatusCode(responseLog, statusLogIn)

//post comment request
responsePost = WS.sendRequest(findTestObject('postComment', [('videoId') : '647004474', ('commentMsg') : 'nice!!']))

//verify if the respont is 201 for good
WS.verifyResponseStatusCode(responsePost, statusPostComment)

//save the comment URL for later
JsonSlurper slurper = new JsonSlurper()

Map parsedJson = slurper.parseText(responsePost.getResponseText())

String responseUri = parsedJson.get('uri')

commentId = responseUri.substring(responseUri.lastIndexOf('/') + 1).trim()

//Open Chrome and go to vimeo.com
WebUI.openBrowser(viemoURL)

//wait for it to load
WebUI.delay(delay)

//verify there is a email slot
WebUI.verifyElementPresent(findTestObject('Page_Log in to Vimeo/input_Log in to Vimeo_email'), 1)

//set the email, passsword and click
WebUI.setText(findTestObject('Page_Log in to Vimeo/input_Log in to Vimeo_email'), email)

WebUI.setText(findTestObject('Page_Log in to Vimeo/input_Log in to Vimeo_password'), password)

WebUI.click(findTestObject('Page_Log in to Vimeo/input_A security code has been sent to your email address_iris_btn iris_btn--lg iris_btn--positive js-email-submit'))

//wait for it to load
WebUI.delay(delay)

//check if we connect
WebUI.verifyElementPresent(findTestObject('Page_Videos on Vimeo/img'), 1)

//check if we connect to the correct user
WebUI.verifyElementText(findTestObject('Page_Videos on Vimeo/li_newaniview'), userName)

//go to video page
WebUI.navigateToUrl('https://vimeo.com/647004474')

//wait for it to load
WebUI.delay(delay)

//check if ewe are in the correct video
WebUI.verifyElementPresent(findTestObject('Page_WRITE YOUR LINE - Ben Buratti on Vimeo/span_WRITE YOUR LINE - Ben Buratti'), 1)

//check if our comment added by using the comment id we saved
TestObject myNewComment = new TestObject('ObjectID')

myNewComment.setSelectorMethod(SelectorMethod.BASIC)

myNewComment.setSelectorValue(SelectorMethod.XPATH, ('//article[@id=\'comment_' + commentId) + '\']/div/div[2]/div/div/p')

myNewComment.setSelectorMethod(SelectorMethod.XPATH)

//Catch exception if not found
try {
    WebUI.verifyElementText(myNewComment, myComment)
}
catch (def stepFailedException) {
    System.out.println('Error: Comment not found')

    System.exit(0)
} 

//go to the next video
WebUI.navigateToUrl(vimeoVideo2)

//wait for it to load
WebUI.delay(delay)

//check if we on the right page
WebUI.verifyElementPresent(findTestObject('Page_STUCK on Vimeo/span_STUCK'), 1)

//get number of likes and views
numOfLikes = WebUI.getText(findTestObject('Page_STUCK on Vimeo/span_249'), FailureHandling.STOP_ON_FAILURE)

numOfViews = WebUI.getText(findTestObject('Page_STUCK on Vimeo/span_12.1K'), FailureHandling.STOP_ON_FAILURE)

