package edu.harvard.mgh.lcs.sprout.forms.study.bean;

import okhttp3.*;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;

public class CallOpenHtml2PdfService {

	OkHttpClient client = new OkHttpClient();

	@Test
	public void test() {

		try {
			createPDF();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void createPDF() throws IOException {
		byte[] pdf = post("http://localhost:9494/convert", html);


		FileOutputStream fos = new FileOutputStream("/Users/slorenz/Desktop/CallOpenHtml2PdfServiceOutput.pdf");
		fos.write(pdf);
		fos.close();



	}

	private byte[] post(String url, String html) throws IOException {
//		MediaType JSON = MediaType.parse("application/json; charset=utf-8");
		MediaType TEXT = MediaType.parse("text/plain; charset=utf-8");
		RequestBody body = RequestBody.create(TEXT, html);
		Request request = new Request.Builder()
				.url(url)
				.post(body)
				.build();
		Response response = client.newCall(request).execute();


		System.out.println("CallOpenHtml2PdfService.post.response.body().contentType(): " + response.body().contentType());

		return response.body().bytes();
	}

	private static String html = "<head><title>Title of the document</title>\n" +
			"<style type=\"text/css\" media=\"print\">\n" +
			"@font-face {\n" +
			"\t\t    font-family: \"Arial Unicode MS\";\n" +
			"\t\t    src: url(\"/sproutassets/components/ttf/ufonts.com_arial-unicode-ms.ttf\");\n" +
			"\t\t}\n" +
			"\t\t.unicoded {\n" +
			"\t\t    font-family: 'Arial Unicode MS', sans-serif;\n" +
			"\t\t    color: blue;\n" +
			"\t\t}\n" +
			"    .well {\n" +
			"        display: none;\n" +
			"    }\n" +
			"    p {\n" +
			"        color: red;\n" +
			"    }\n" +
			"    .red {\n" +
			"        color: red;\n" +
			"    }\n" +
			"    .img-checkbox {\n" +
			"        height: 20px;\n" +
			"        v-align: top;\n" +
			"    }\n" +
			"</style>\n" +
			"</head>\n" +
			"\n" +
			"<h1>M &amp; M's</h1>\n" +
			"\n" +
			"Hello Steve\n" +
			"<br/><br/>\n" +
			"\n" +
			"This is a test....\n" +
			"\n" +
			"<br/>\n" +
			"<strong>Cat:</strong> cat...\n" +
			"<br/>\n" +
			"<strong>Cow:</strong> c\"ow...\n" +
			"<br/>\n" +
			"<strong>Train:</strong> train...\n" +
			"\n" +
			"safdsafasfthis is a test....\n" +
			"\n" +
			"now\n" +
			"\n" +
			"\n" +
			"\n" +
			"<img src=\"http://deltamultimediaservices.com/wp-content/uploads/2014/10/Medical-Banner.jpg\" style='height: 30px;'/>\n" +
			"<h1>Sample Narrative</h1>\n" +
			"<p>This should be red</p>\n" +
			"<span class=\"red\">This should be red</span>\n" +
			"<h2>\n" +
			"Hello\n" +
			"Niño!\n" +
			"</h2>\n" +
			"check: &#10003; \\u2713<br/>\n" +
			"<span class='unicoded'>check: &#10003; \\u2713</span><br/>\n" +
			"This is the caregiver mode\n" +
			"<h3>Subject: Fred (Date of Birth: 10/10/1999)</h3>";



	private static String html1 = "<head>\n" +
			"<link rel=\"stylesheet\" type=\"text/css\" href=\"https://sprouttest.partners.org/sproutassets/brands/pcori-dsp/styles/main/css/output-form.css\" media=\"all\"></link>\n" +
			"\n" +
			"<script type=\"text/javascript\">\n" +
			"    $(\"document\").ready (function(){\n" +
			"\n" +
			"    $(\".why\").click(function() {\n" +
			"        $(this).parent().next('.well').toggle();\n" +
			"    });\n" +
			"\n" +
			"});\n" +
			"</script>\n" +
			"\n" +
			"<style media=\"screen\">\n" +
			"    .well {\n" +
			"        display: none;\n" +
			"    }\n" +
			"</style>\n" +
			"</head>\n" +
			"\n" +
			"<!-- CAREGIVER SECTION -->\n" +
			"<!-- CAREGIVER SECTION -->\n" +
			"<!-- CAREGIVER SECTION -->\n" +
			"\n" +
			"\n" +
			"<!-- End caregiver plan -->\n" +
			"\n" +
			"\n" +
			"\n" +
			"<!-- PCP SECTION -->\n" +
			"<!-- PCP SECTION -->\n" +
			"<!-- PCP SECTION -->\n" +
			"\n" +

//	"<div class=\"pcp-wrapper\">\n" +
			"\n" +
			"    <table width=\"90%\" style=\"margin-right:30px; margin-left:30px;\">\n" +
			"        <tr>\n" +
			"            <td align=\"left\"><img src=\"https://sprouttest.partners.org/sproutassets/brands/pcori-dsp/images/mgh-logo.png\" alt=\"Massachusetts General Hospital Logo\" width=\"252\" height=\"52\"></img></td>\n" +
			"            <td align=\"right\"><img src=\"https://sprouttest.partners.org/sproutassets/brands/pcori-dsp/images/harvard-medical-school-logo.png\" alt=\"Harvard Medical School Logo\" width=\"229\" height=\"52\"></img></td>\n" +
			"        </tr>\n" +
			"    </table>\n" +
			"    <br></br>\n" +
			"\n" +

	"    <p>07/20/2017</p>\n" +
			"\n" +
			"    <p>Dear Mr. Shaw,</p>\n" +
			"\n" +
			"    <p>The caregiver of your patient, <strong>H Test (DOB 01/01/2000)</strong>, is participating in a research project aimed at improving healthcare outcomes for patients with Down syndrome.  The project is coordinated by a team of clinicians and researchers at Massachusetts General Hospital in Boston and is funded by a grant from the Patient-Centered Outcomes Research Institute.  More information about our project can be found at <a href=\"http://www.downsyndromeclinictoyou.com\" target=\"_blank\">www.downsyndromeclinictoyou.com</a>.</p>\n" +
			"\n" +
			"    <p>The caregiver of your patient completed an online questionnaire which generated automated suggestions for testing and treatment that are anchored on published practice guidelines and/or expert consensus.  The online tool encouraged <strong>H</strong>'s caregiver to share and discuss these with you.</p>\n" +
			"\n" +
			"    <p>On the next page, you will find a checklist of these recommendations and list of educational resources specific for Down syndrome.  We hope you will find them helpful during your next clinical visit with this patient.</p>\n" +
			"\n" +
			"    <p>Sincerely,</p>\n" +
			"\n" +
			"    <p>MGH Down Syndrome Program Research Team</p>\n" +
			"    <br></br><br></br>\n" +
			"\n" +
			"    <p>P.S.  As we are testing the efficacy of this intervention alone, we are not available for clinical consultation by phone or e-mail.  If you have any technical questions, however, regarding the accessibility of this study's resources, please e-mail <a href=\"mailto:dsc2u@mgh.harvard.edu\">dsc2u@mgh.harvard.edu</a>.</p>\n" +
			"    <br></br>\n" +
			"\n" +
			"\n" +
			"\n" +






	"    <p style=\"border: solid 1pt; page-break-after: always\"></p>\n" +
			"\n" +
			"    <!-- End pcp cover -->\n" +
			"\n" +
			"\n" +
			"\n" +
			"    <h1>Personalized Checklist for H's Provider</h1>\n" +
			"\n" +
			"    <p>The caregiver of your patient completed an online questionnaire which generated automated suggestions for testing and treatment that are anchored on published practice guidelines and/or expert consensus.</p>\n" +
			"\n" +
			"    <p>This checklist does not establish a health care provider-patient relationship. It is not an attempt to practice medicine or provide specific clinical advice. It is intended to provide useful information to you for reference and educational purposes only. It contains auto-programmed suggestions based on answers provided by your patient's caregiver to the Down Syndrome Clinic to You (DSC2U) survey and on national guidelines. It was not prepared or reviewed by a clinician specifically for your patient. The content of this letter is not meant to be complete or exhaustive or to be a substitute for professional medical advice, diagnosis or treatment. You are responsible for exercising independent judgment about the contents of this letter and for recommending and implementing any care or other course of action for your patient.</p>\n" +
			"\n" +
			"    <br/>\n" +
			"\n" +
			"    <p>  <img src=\"https://sprouttest.partners.org/sproutassets/brands/pcori-dsp/images/checkmark.png\" class=\"img-checkmark\"></img> You might choose to check each item when completed for H.</p>\n" +
			"\n" +
			"    <!-- End pcp intro -->\n" +
			"\n" +
			"    <!-- PCP URGENT -->\n" +
			"    <!-- PCP URGENT -->\n" +
			"    <!-- PCP URGENT\n" +
			"\n" +
			"    <hr></hr>\n" +
			"    <h2><font color=\"red\">Urgent Suggestions for H</font></h2>\n" +
			"\n" +
			"    insert cervical xray\n" +
			"\n" +
			"    End pcp urgent -->\n" +
			"\n" +
			"\n" +
			"    <!-- PCP LABS TESTS PROCEDURES -->\n" +
			"    <!-- PCP LABS TESTS PROCEDURES -->\n" +
			"    <!-- PCP LABS TESTS PROCEDURES -->\n" +
			"\n" +
			"    <hr></hr>\n" +
			"    <h2>Recommended Labs, Tests, and Procedures for H</h2>\n" +
			"    <br></br>\n" +
			"\n" +
			"        <table class=\"table-checklist\">\n" +
			"            <tr>\n" +
			"                <td class=\"td-checkbox\"><img src=\"https://sprouttest.partners.org/sproutassets/brands/pcori-dsp/images/checkbox.png\" class=\"img-checkbox\"></img></td>\n" +
			"                <td><p><strong>Audiogram/hearing test.</strong> For ages 1 to 21, AAP recommends annual audiograms given increased incidence of hearing loss in this population.</p></td>\n" +
			"            </tr>\n" +
			"        </table>\n" +
			"\n" +
			"\n" +
			"\n" +
			"\n" +
			"        <table class=\"table-checklist\">\n" +
			"            <tr>\n" +
			"                <td class=\"td-checkbox\"><img src=\"https://sprouttest.partners.org/sproutassets/brands/pcori-dsp/images/checkbox.png\" class=\"img-checkbox\"></img></td>\n" +
			"                <td><p><strong>Ophthalmology Evaluation:</strong> AAP recommends an ophthalmologic exam at least every three years for people with Down syndrome, ages 13 and 21.</p></td>\n" +
			"            </tr>\n" +
			"        </table>\n" +
			"\n" +
			"\n" +
			"\n" +
			"\n" +
			"\n" +
			"\n" +
			"\n" +
			"\n" +
			"\n" +
			"\n" +
			"\n" +






	"        <table class=\"table-checklist\">\n" +
			"            <tr>\n" +
			"                <td class=\"td-checkbox\"><img src=\"https://sprouttest.partners.org/sproutassets/brands/pcori-dsp/images/checkbox.png\" class=\"img-checkbox\"></img></td>\n" +
			"                <td><p><strong>Celiac screen</strong> (total IgA &amp; TTG-IgA)<strong>.</strong> Has caregiver indicated that within the past month of completing our intake, she had\n" +
			"                        nausea,\n" +
			"                        vomiting.\n" +
			"                Has caregiver is also not sure whether or not H has ever had celiac testing done. Celiac disease is more common in people with Down syndrome. You should consider testing if celiac testing has never been done, or if it has not been done within the past 12 months.</p></td>\n" +
			"            </tr>\n" +
			"        </table>\n" +
			"\n" +
			"\n" +
			"\n";


	String t = "    <!-- insert sleep apnea labs -->\n" +
			"\n" +
			"    <!-- insert thyroid labs -->\n" +
			"\n" +
			"        <table class=\"table-checklist\">\n" +
			"            <tr>\n" +
			"                <td class=\"td-checkbox\"><img src=\"https://sprouttest.partners.org/sproutassets/brands/pcori-dsp/images/checkbox.png\" class=\"img-checkbox\"></img></td>\n" +
			"                <td>\n" +
			"                    <p><strong>Hemoglobin level.</strong> H�s caregiver indicated that within the past 12 months of completing our intake, H has not had a hemoglobin level.  The AAP recommends that everyone with Down syndrome have a hemoglobin level checked annually to assess for iron-deficiency anemia.  If Hg < 11, the AAP recommends one of these follow-up labs: (1) ferritin and CRP or (2) reticulocyte hemoglobin (CHr).  If (1) CRP is normal and ferritin is low or (2) CHr is low, then iron augmentation through diet or supplements is recommended.</p>\n" +
			"                </td>\n" +
			"            </tr>\n" +
			"        </table>\n" +
			"\n" +
			"\n" +
			"\n" +
			"\n" +
			"    <!-- insert vfss labs -->\n" +
			"\n" +
			"        <table class=\"table-checklist\">\n" +
			"            <tr>\n" +
			"                <td class=\"td-checkbox\"><img src=\"https://sprouttest.partners.org/sproutassets/brands/pcori-dsp/images/checkbox.png\" class=\"img-checkbox\"></img></td>\n" +
			"                <td>\n" +
			"                    <p><strong>Dental visit.</strong> H�s caregiver indicated that H has not had a dental exam within the past 6 months.</p>\n" +
			"                </td>\n" +
			"            </tr>\n" +
			"        </table>\n" +
			"\n" +
			"\n" +
			"\n" +
			"\n" +
			"\n" +
			"\n" +
			"\n" +
			"\n" +
			"\n" +
			"\n" +
			"    <!-- End PCP labs tests procedures -->\n" +
			"\n" +
			"    <!-- PCP RESOURCES -->\n" +
			"    <!-- PCP RESOURCES -->\n" +
			"    <!-- PCP RESOURCES -->\n" +
			"\n" +
			"    <br></br>\n" +
			"    <p style=\"border: solid 1pt; page-break-after: always\"></p>\n" +
			"    <h2>OTHER INFORMATION, RESOURCES, and SUPPORTS for H</h2>\n" +
			"    <h3>For your information, we provided Hs caregiver links to the following online resources and information.  If you would like online access to these resources, please go to !!!!!URL to be inserted!!!!!.</h3>\n" +
			"\n" +
			"\n" +
			"\n" +
			"    <br></br>\n" +
			"    <h2>Health and Wellness Resources</h2>\n" +
			"    <br></br>\n" +
			"\n" +
			"\n" +
			"\n" +
			"\n" +
			"\n" +
			"\n" +
			"\n" +
			"\n" +
			"\n" +
			"    <!-- insert sleep apnea resources -->\n" +
			"\n" +
			"    <!-- End PCP resources -->\n" +
			"\n" +
			"<!-- End caregiver health wellness resources -->\n" +
			"\n" +
			"    <!-- PCP LIFESKILLS RESOURCES -->\n" +
			"    <!-- PCP LIFESKILLS RESOURCES -->\n" +
			"    <!-- PCP LIFESKILLS RESOURCES -->\n" +
			"\n" +
			"        <hr></hr>\n" +
			"        <h2>Life Skills</h2>\n" +
			"\n" +
			"\n" +
			"\n" +
			"\n" +
			"        <table class=\"table-checklist\">\n" +
			"            <tr>\n" +
			"                <td class=\"td-checkbox\">?</td>\n" +
			"                <td>\n" +
			"                    <p>H�s caregiver would like for H to learn how to describe how she is feeling to her doctor.</p>\n" +
			"                </td>\n" +
			"            </tr>\n" +
			"        </table>\n" +
			"\n" +
			"\n" +
			"        <table class=\"table-checklist\">\n" +
			"            <tr>\n" +
			"                <td class=\"td-checkbox\">?</td>\n" +
			"                <td>\n" +
			"                    <p>H�s caregiver would like for H to learn what each medication is for.</p>\n" +
			"                </td>\n" +
			"            </tr>\n" +
			"        </table>\n" +
			"\n" +
			"\n" +
			"\n" +
			"\n" +
			"        <table class=\"table-checklist\">\n" +
			"            <tr>\n" +
			"                <td class=\"td-checkbox\">?</td>\n" +
			"                <td>\n" +
			"                    <p>H�s caregiver would like for H to learn about the differences between healthy and unhealthy foods.</p>\n" +
			"                </td>\n" +
			"            </tr>\n" +
			"        </table>\n" +
			"\n" +
			"        <table class=\"table-checklist\">\n" +
			"            <tr>\n" +
			"                <td class=\"td-checkbox\">?</td>\n" +
			"                <td>\n" +
			"                    <p>H�s caregiver would like for H to learn about the risks of alcohol, drugs, and tobacco use.</p>\n" +
			"                </td>\n" +
			"            </tr>\n" +
			"        </table>\n" +
			"\n" +
			"\n" +
			"\n" +
			"        <table class=\"table-checklist\">\n" +
			"            <tr>\n" +
			"                <td class=\"td-checkbox\">?</td>\n" +
			"                <td>\n" +
			"                    <p>H�s caregiver would like for H to exercise regularly.</p>\n" +
			"                </td>\n" +
			"            </tr>\n" +
			"        </table>\n" +
			"\n" +
			"\n" +
			"\n" +
			"        <table class=\"table-checklist\">\n" +
			"            <tr>\n" +
			"                <td class=\"td-checkbox\">?</td>\n" +
			"                <td>\n" +
			"                    <p>H�s caregiver would like for H to learn how to use public transportation on her  own.</p>\n" +
			"                </td>\n" +
			"            </tr>\n" +
			"        </table>\n" +
			"\n" +
			"\n" +
			"\n" +
			"        <table class=\"table-checklist\">\n" +
			"            <tr>\n" +
			"                <td class=\"td-checkbox\">?</td>\n" +
			"                <td>\n" +
			"                    <p>H�s caregiver would like for H to understand sexual boundaries and privacy.</p>\n" +
			"                </td>\n" +
			"            </tr>\n" +
			"        </table>\n" +
			"\n" +
			"\n" +
			"\n" +
			"        <table class=\"table-checklist\">\n" +
			"            <tr>\n" +
			"                <td class=\"td-checkbox\">?</td>\n" +
			"                <td>\n" +
			"                    <p>H�s caregiver would like for H be able to dress herself.</p>\n" +
			"                </td>\n" +
			"            </tr>\n" +
			"        </table>\n" +
			"\n" +
			"\n" +
			"        <table class=\"table-checklist\">\n" +
			"            <tr>\n" +
			"                <td class=\"td-checkbox\">?</td>\n" +
			"                <td>\n" +
			"                    <p>H�s caregiver would like for H to prepare her  own meals.</p>\n" +
			"                </td>\n" +
			"            </tr>\n" +
			"        </table>\n" +
			"\n" +
			"\n" +
			"\n" +
			"        <table class=\"table-checklist\">\n" +
			"            <tr>\n" +
			"                <td class=\"td-checkbox\">?</td>\n" +
			"                <td>\n" +
			"                    <p>H�s caregiver would like for H to have a plan for what she  will do after finishing high school.</p>\n" +
			"                </td>\n" +
			"            </tr>\n" +
			"        </table>\n" +
			"\n" +
			"\n" +
			"\n" +
			"        <table class=\"table-checklist\">\n" +
			"            <tr>\n" +
			"                <td class=\"td-checkbox\">?</td>\n" +
			"                <td>\n" +
			"                    <p>H�s caregiver would like for H to learn where to find her doctor�s phone number.</p>\n" +
			"                </td>\n" +
			"            </tr>\n" +
			"        </table>\n" +
			"\n" +
			"        <table class=\"table-checklist\">\n" +
			"            <tr>\n" +
			"                <td class=\"td-checkbox\">?</td>\n" +
			"                <td>\n" +
			"                    <p>H�s caregiver would like for H to learn how to ask questions of her doctors.</p>\n" +
			"                </td>\n" +
			"            </tr>\n" +
			"        </table>\n" +
			"\n" +
			"\n" +
			"        <table class=\"table-checklist\">\n" +
			"            <tr>\n" +
			"                <td class=\"td-checkbox\">?</td>\n" +
			"                <td>\n" +
			"                    <p>H�s caregiver would like for H to learn how to find her  medications.</p>\n" +
			"                </td>\n" +
			"            </tr>\n" +
			"        </table>\n" +
			"\n" +
			"\n" +
			"        <table class=\"table-checklist\">\n" +
			"            <tr>\n" +
			"                <td class=\"td-checkbox\">?</td>\n" +
			"                <td>\n" +
			"                    <p>H�s caregiver would like for H to learn how to take her  medications every day on her own.</p>\n" +
			"                </td>\n" +
			"            </tr>\n" +
			"        </table>\n" +
			"\n" +
			"        <table class=\"table-checklist\">\n" +
			"            <tr>\n" +
			"                <td class=\"td-checkbox\">?</td>\n" +
			"                <td>\n" +
			"                    <p>H�s caregiver would like for H to learn how to refill her  prescriptions on her  own.</p>\n" +
			"                </td>\n" +
			"            </tr>\n" +
			"        </table>\n" +
			"\n" +
			"        <table class=\"table-checklist\">\n" +
			"            <tr>\n" +
			"                <td class=\"td-checkbox\">?</td>\n" +
			"                <td>\n" +
			"                    <p>H�s caregiver would like for H to learn how to swallow whole pills.</p>\n" +
			"                </td>\n" +
			"            </tr>\n" +
			"        </table>\n" +
			"\n" +
			"\n" +
			"\n" +
			"        <table class=\"table-checklist\">\n" +
			"            <tr>\n" +
			"                <td class=\"td-checkbox\">?</td>\n" +
			"                <td>\n" +
			"                    <p>H�s caregiver would like for H to learn how to call 911 if there is an emergency.</p>\n" +
			"                </td>\n" +
			"            </tr>\n" +
			"        </table>\n" +
			"\n" +
			"        <table class=\"table-checklist\">\n" +
			"            <tr>\n" +
			"                <td class=\"td-checkbox\">?</td>\n" +
			"                <td>\n" +
			"                    <p>H�s caregiver would like for H to be able to sleep 7 to 8 hours per night.</p>\n" +
			"                </td>\n" +
			"            </tr>\n" +
			"        </table>\n" +
			"\n" +
			"\n" +
			"        <table class=\"table-checklist\">\n" +
			"            <tr>\n" +
			"                <td class=\"td-checkbox\">?</td>\n" +
			"                <td>\n" +
			"                    <p>H�s caregiver would like for H to learn how to provide her  personal information when needed. </p>\n" +
			"                </td>\n" +
			"            </tr>\n" +
			"        </table>\n" +
			"\n" +
			"        <table class=\"table-checklist\">\n" +
			"            <tr>\n" +
			"                <td class=\"td-checkbox\">?</td>\n" +
			"                <td>\n" +
			"                    <p>H�s caregiver would like for H to learn how to tell the difference between a stranger and a friend.</p>\n" +
			"                </td>\n" +
			"            </tr>\n" +
			"        </table>\n" +
			"\n" +
			"\n" +
			"        <table class=\"table-checklist\">\n" +
			"            <tr>\n" +
			"                <td class=\"td-checkbox\">?</td>\n" +
			"                <td>\n" +
			"                    <p>H�s caregiver would like for H to learn how to do household chores.</p>\n" +
			"                </td>\n" +
			"            </tr>\n" +
			"        </table>\n" +
			"\n" +
			"        <table class=\"table-checklist\">\n" +
			"            <tr>\n" +
			"                <td class=\"td-checkbox\">?</td>\n" +
			"                <td>\n" +
			"                    <p>H�s caregiver would like for H to learn how to manage her period.</p>\n" +
			"                </td>\n" +
			"            </tr>\n" +
			"        </table>\n" +
			"\n" +
			"\n" +
			"        <table class=\"table-checklist\">\n" +
			"            <tr>\n" +
			"                <td class=\"td-checkbox\">?</td>\n" +
			"                <td>\n" +
			"                    <p>H�s caregiver would like for H to learn how to brush her  teeth on her  own.</p>\n" +
			"                </td>\n" +
			"            </tr>\n" +
			"        </table>\n" +
			"\n" +
			"        <table class=\"table-checklist\">\n" +
			"            <tr>\n" +
			"                <td class=\"td-checkbox\">?</td>\n" +
			"                <td>\n" +
			"                    <p>H�s caregiver would like for H to be able to use a public restroom on her  own.</p>\n" +
			"                </td>\n" +
			"            </tr>\n" +
			"        </table>\n" +
			"\n" +
			"\n" +
			"        <table class=\"table-checklist\">\n" +
			"            <tr>\n" +
			"                <td class=\"td-checkbox\">?</td>\n" +
			"                <td>\n" +
			"                    <p>H�s caregiver would like for H to be able to bathe/shower her self.</p>\n" +
			"                </td>\n" +
			"            </tr>\n" +
			"        </table>\n" +
			"\n" +
			"\n" +
			"        <table class=\"table-checklist\">\n" +
			"            <tr>\n" +
			"                <td class=\"td-checkbox\">?</td>\n" +
			"                <td>\n" +
			"                    <p>H�s caregiver would like for H to be able to do her  own laundry.</p>\n" +
			"                </td>\n" +
			"            </tr>\n" +
			"        </table>\n" +
			"\n" +
			"        <table class=\"table-checklist\">\n" +
			"            <tr>\n" +
			"                <td class=\"td-checkbox\">?</td>\n" +
			"                <td>\n" +
			"                    <p>H�s caregiver would like for H to learn how to find her  insurance card.</p>\n" +
			"                </td>\n" +
			"            </tr>\n" +
			"        </table>\n" +
			"\n" +
			"\n" +
			"\n" +
			"        <table class=\"table-checklist\">\n" +
			"            <tr>\n" +
			"                <td class=\"td-checkbox\">?</td>\n" +
			"                <td>\n" +
			"                    <p>H�s caregiver would like for H to name at least two adults she  can ask for help when needed.</p>\n" +
			"                </td>\n" +
			"            </tr>\n" +
			"        </table>\n" +
			"\n" +
			"<!-- End pcp lifeskills resources -->\n" +
			"\n" +
			"    <!-- PCP CAREGIVER RESOURCES -->\n" +
			"    <!-- PCP CAREGIVER RESOURCES -->\n" +
			"    <!-- PCP CAREGIVER RESOURCES -->\n" +
			"\n" +
			"    <hr></hr>\n" +
			"    <h2>Caregiver Information</h2>\n" +
			"    <br></br>\n" +
			"\n" +
			"\n" +
			"\n" +
			"\n" +
			"\n" +
			"        <table class=\"table-checklist\">\n" +
			"            <tr>\n" +
			"                <td class=\"td-checkbox\">?</td>\n" +
			"                <td>\n" +
			"                    <p>GiGi�s Playhouse</p>\n" +
			"                </td>\n" +
			"            </tr>\n" +
			"        </table>\n" +
			"\n" +
			"        <table class=\"table-checklist\">\n" +
			"            <tr>\n" +
			"                <td class=\"td-checkbox\">?</td>\n" +
			"                <td>\n" +
			"                    <p>The Global Down Syndrome Foundation</p>\n" +
			"                </td>\n" +
			"            </tr>\n" +
			"        </table>\n" +
			"\n" +
			"\n" +
			"\n" +
			"        <table class=\"table-checklist\">\n" +
			"            <tr>\n" +
			"                <td class=\"td-checkbox\">?</td>\n" +
			"                <td>\n" +
			"                    <p>National Down Syndrome Society (NDSS)</p>\n" +
			"                </td>\n" +
			"            </tr>\n" +
			"        </table>\n" +
			"\n" +
			"\n" +
			"\n" +
			"        <table class=\"table-checklist\">\n" +
			"            <tr>\n" +
			"                <td class=\"td-checkbox\">?</td>\n" +
			"                <td>\n" +
			"                    <p>We provided a link to specific book pages discussing different guardianship options in the USA</p>\n" +
			"                </td>\n" +
			"            </tr>\n" +
			"        </table>\n" +
			"\n" +
			"        <table class=\"table-checklist\">\n" +
			"            <tr>\n" +
			"                <td class=\"td-checkbox\">?</td>\n" +
			"                <td>\n" +
			"                    <p>We provided a link to resources for adults with disabilities applying for social security</p>\n" +
			"                </td>\n" +
			"            </tr>\n" +
			"        </table>\n" +
			"\n" +
			"        <table class=\"table-checklist\">\n" +
			"            <tr>\n" +
			"                <td class=\"td-checkbox\">?</td>\n" +
			"                <td>\n" +
			"                    <p>We provided resources on setting up a special needs trusts  or an ABLE account</p>\n" +
			"                </td>\n" +
			"            </tr>\n" +
			"        </table>\n" +
			"\n" +
			"\n" +
			"\n" +
			"<!-- end pcp caregiver info -->\n" +
			"\n" +
			"\n" +
			"\n" +
			"</div><!-- .pcp-wrapper -->\n" +
			"\n" +
			"\n" +
			"\n" +
			"\n";
//	private static String html = "<head>\n" +
//			"<link rel=\"stylesheet\" type=\"text/css\" href=\"https://sprouttest.partners.org/sproutassets/brands/pcori-dsp/styles/main/css/output-form.css\" media=\"all\"></link>\n" +
//			"\n" +
//			"<script type=\"text/javascript\">\n" +
//			"    $(\"document\").ready (function(){\n" +
//			"\n" +
//			"    $(\".why\").click(function() {\n" +
//			"        $(this).parent().next('.well').toggle();\n" +
//			"    });\n" +
//			"\n" +
//			"});\n" +
//			"</script>\n" +
//			"\n" +
//			"<style media=\"screen\">\n" +
//			"    .well {\n" +
//			"        display: none;\n" +
//			"    }\n" +
//			"</style>\n" +
//			"</head>\n" +
//			"\n" +
//			"<!-- CAREGIVER SECTION -->\n" +
//			"<!-- CAREGIVER SECTION -->\n" +
//			"<!-- CAREGIVER SECTION -->\n" +
//			"\n" +
//			"\n" +
//			"<!-- End caregiver plan -->\n" +
//			"\n" +
//			"\n" +
//			"\n" +
//			"<!-- PCP SECTION -->\n" +
//			"<!-- PCP SECTION -->\n" +
//			"<!-- PCP SECTION -->\n" +
//			"\n" +
//			"<div class=\"pcp-wrapper\">\n" +
//			"\n" +
//			"    <table width=\"90%\" style=\"margin-right:30px; margin-left:30px;\">\n" +
//			"        <tr>\n" +
//			"            <td align=\"left\"><img src=\"https://sprouttest.partners.org/sproutassets/brands/pcori-dsp/images/mgh-logo.png\" alt=\"Massachusetts General Hospital Logo\" width=\"252\" height=\"52\"></img></td>\n" +
//			"            <td align=\"right\"><img src=\"https://sprouttest.partners.org/sproutassets/brands/pcori-dsp/images/harvard-medical-school-logo.png\" alt=\"Harvard Medical School Logo\" width=\"229\" height=\"52\"></img></td>\n" +
//			"        </tr>\n" +
//			"    </table>\n" +
//			"    <br></br>\n" +
//			"\n" +
//			"    <p>07/20/2017</p>\n" +
//			"\n" +
//			"    <p>Dear Mr. Shaw,</p>\n" +
//			"\n" +
//			"    <p>The caregiver of your patient, <strong>H Test (DOB 01/01/2000)</strong>, is participating in a research project aimed at improving healthcare outcomes for patients with Down syndrome.  The project is coordinated by a team of clinicians and researchers at Massachusetts General Hospital in Boston and is funded by a grant from the Patient-Centered Outcomes Research Institute.  More information about our project can be found at <a href=\"http://www.downsyndromeclinictoyou.com\" target=\"_blank\">www.downsyndromeclinictoyou.com</a>.</p>\n" +
//			"\n" +
//			"    <p>The caregiver of your patient completed an online questionnaire which generated automated suggestions for testing and treatment that are anchored on published practice guidelines and/or expert consensus.  The online tool encouraged <strong>H</strong>'s caregiver to share and discuss these with you.</p>\n" +
//			"\n" +
//			"    <p>On the next page, you will find a checklist of these recommendations and list of educational resources specific for Down syndrome.  We hope you will find them helpful during your next clinical visit with this patient.</p>\n" +
//			"\n" +
//			"    <p>Sincerely,</p>\n" +
//			"\n" +
//			"    <p>MGH Down Syndrome Program Research Team</p>\n" +
//			"    <br></br><br></br>\n" +
//			"\n" +
//			"    <p>P.S.  As we are testing the efficacy of this intervention alone, we are not available for clinical consultation by phone or e-mail.  If you have any technical questions, however, regarding the accessibility of this study's resources, please e-mail <a href=\"mailto:dsc2u@mgh.harvard.edu\">dsc2u@mgh.harvard.edu</a>.</p>\n" +
//			"    <br></br>\n" +
//			"\n" +
//			"\n" +
//			"\n" +
//			"    <p style=\"border: solid 1pt; page-break-after: always\"></p>\n" +
//			"\n" +
//			"    <!-- End pcp cover -->\n" +
//			"\n" +
//			"\n" +
//			"\n" +
//			"    <h1>Personalized Checklist for H's Provider</h1>\n" +
//			"\n" +
//			"    <p>The caregiver of your patient completed an online questionnaire which generated automated suggestions for testing and treatment that are anchored on published practice guidelines and/or expert consensus.</p>\n" +
//			"\n" +
//			"    <p>This checklist does not establish a health care provider-patient relationship. It is not an attempt to practice medicine or provide specific clinical advice. It is intended to provide useful information to you for reference and educational purposes only. It contains auto-programmed suggestions based on answers provided by your patient's caregiver to the Down Syndrome Clinic to You (DSC2U) survey and on national guidelines. It was not prepared or reviewed by a clinician specifically for your patient. The content of this letter is not meant to be complete or exhaustive or to be a substitute for professional medical advice, diagnosis or treatment. You are responsible for exercising independent judgment about the contents of this letter and for recommending and implementing any care or other course of action for your patient.</p>\n" +
//			"\n" +
//			"    <br/>\n" +
//			"\n" +
//			"    <p>  <img src=\"https://sprouttest.partners.org/sproutassets/brands/pcori-dsp/images/checkmark.png\" class=\"img-checkmark\"></img> You might choose to check each item when completed for H.</p>\n" +
//			"\n" +
//			"    <!-- End pcp intro -->\n" +
//			"\n" +
//			"    <!-- PCP URGENT -->\n" +
//			"    <!-- PCP URGENT -->\n" +
//			"    <!-- PCP URGENT\n" +
//			"\n" +
//			"    <hr></hr>\n" +
//			"    <h2><font color=\"red\">Urgent Suggestions for H</font></h2>\n" +
//			"\n" +
//			"    insert cervical xray\n" +
//			"\n" +
//			"    End pcp urgent -->\n" +
//			"\n" +
//			"\n" +
//			"    <!-- PCP LABS TESTS PROCEDURES -->\n" +
//			"    <!-- PCP LABS TESTS PROCEDURES -->\n" +
//			"    <!-- PCP LABS TESTS PROCEDURES -->\n" +
//			"\n" +
//			"    <hr></hr>\n" +
//			"    <h2>Recommended Labs, Tests, and Procedures for H</h2>\n" +
//			"    <br></br>\n" +
//			"\n" +
//			"        <table class=\"table-checklist\">\n" +
//			"            <tr>\n" +
//			"                <td class=\"td-checkbox\"><img src=\"https://sprouttest.partners.org/sproutassets/brands/pcori-dsp/images/checkbox.png\" class=\"img-checkbox\"></img></td>\n" +
//			"                <td><p><strong>Audiogram/hearing test.</strong> For ages 1 to 21, AAP recommends annual audiograms given increased incidence of hearing loss in this population.</p></td>\n" +
//			"            </tr>\n" +
//			"        </table>\n" +
//			"\n" +
//			"\n" +
//			"\n" +
//			"\n" +
//			"        <table class=\"table-checklist\">\n" +
//			"            <tr>\n" +
//			"                <td class=\"td-checkbox\"><img src=\"https://sprouttest.partners.org/sproutassets/brands/pcori-dsp/images/checkbox.png\" class=\"img-checkbox\"></img></td>\n" +
//			"                <td><p><strong>Ophthalmology Evaluation:</strong> AAP recommends an ophthalmologic exam at least every three years for people with Down syndrome, ages 13 and 21.</p></td>\n" +
//			"            </tr>\n" +
//			"        </table>\n" +
//			"\n" +
//			"\n" +
//			"\n" +
//			"\n" +
//			"\n" +
//			"\n" +
//			"\n" +
//			"\n" +
//			"\n" +
//			"\n" +
//			"\n" +
//			"        <table class=\"table-checklist\">\n" +
//			"            <tr>\n" +
//			"                <td class=\"td-checkbox\"><img src=\"https://sprouttest.partners.org/sproutassets/brands/pcori-dsp/images/checkbox.png\" class=\"img-checkbox\"></img></td>\n" +
//			"                <td><p><strong>Celiac screen</strong> (total IgA & TTG-IgA)<strong>.</strong> H�s caregiver indicated that within the past month of completing our intake, she had\n" +
//			"\n" +
//			"\n" +
//			"                        nausea,\n" +
//			"                        vomiting.\n" +
//			"\n" +
//			"\n" +
//			"\n" +
//			"\n" +
//			"\n" +
//			"\n" +
//			"\n" +
//			"\n" +
//			"\n" +
//			"\n" +
//			"\n" +
//			"\n" +
//			"\n" +
//			"\n" +
//			"\n" +
//			"\n" +
//			"\n" +
//			"\n" +
//			"\n" +
//			"\n" +
//			"\n" +
//			"\n" +
//			"                H�s caregiver is also not sure whether or not H has ever had celiac testing done. Celiac disease is more common in people with Down syndrome. You should consider testing if celiac testing has never been done, or if it has not been done within the past 12 months.</p></td>\n" +
//			"            </tr>\n" +
//			"        </table>\n" +
//			"\n" +
//			"\n" +
//			"\n" +
//			"    <!-- insert sleep apnea labs -->\n" +
//			"\n" +
//			"    <!-- insert thyroid labs -->\n" +
//			"\n" +
//			"        <table class=\"table-checklist\">\n" +
//			"            <tr>\n" +
//			"                <td class=\"td-checkbox\"><img src=\"https://sprouttest.partners.org/sproutassets/brands/pcori-dsp/images/checkbox.png\" class=\"img-checkbox\"></img></td>\n" +
//			"                <td>\n" +
//			"                    <p><strong>Hemoglobin level.</strong> H�s caregiver indicated that within the past 12 months of completing our intake, H has not had a hemoglobin level.  The AAP recommends that everyone with Down syndrome have a hemoglobin level checked annually to assess for iron-deficiency anemia.  If Hg < 11, the AAP recommends one of these follow-up labs: (1) ferritin and CRP or (2) reticulocyte hemoglobin (CHr).  If (1) CRP is normal and ferritin is low or (2) CHr is low, then iron augmentation through diet or supplements is recommended.</p>\n" +
//			"                </td>\n" +
//			"            </tr>\n" +
//			"        </table>\n" +
//			"\n" +
//			"\n" +
//			"\n" +
//			"\n" +
//			"    <!-- insert vfss labs -->\n" +
//			"\n" +
//			"        <table class=\"table-checklist\">\n" +
//			"            <tr>\n" +
//			"                <td class=\"td-checkbox\"><img src=\"https://sprouttest.partners.org/sproutassets/brands/pcori-dsp/images/checkbox.png\" class=\"img-checkbox\"></img></td>\n" +
//			"                <td>\n" +
//			"                    <p><strong>Dental visit.</strong> H�s caregiver indicated that H has not had a dental exam within the past 6 months.</p>\n" +
//			"                </td>\n" +
//			"            </tr>\n" +
//			"        </table>\n" +
//			"\n" +
//			"\n" +
//			"\n" +
//			"\n" +
//			"\n" +
//			"\n" +
//			"\n" +
//			"\n" +
//			"\n" +
//			"\n" +
//			"    <!-- End PCP labs tests procedures -->\n" +
//			"\n" +
//			"    <!-- PCP RESOURCES -->\n" +
//			"    <!-- PCP RESOURCES -->\n" +
//			"    <!-- PCP RESOURCES -->\n" +
//			"\n" +
//			"    <br></br>\n" +
//			"    <p style=\"border: solid 1pt; page-break-after: always\"></p>\n" +
//			"    <h2>OTHER INFORMATION, RESOURCES, and SUPPORTS for H</h2>\n" +
//			"    <h3>For your information, we provided Hs caregiver links to the following online resources and information.  If you would like online access to these resources, please go to !!!!!URL to be inserted!!!!!.</h3>\n" +
//			"\n" +
//			"\n" +
//			"\n" +
//			"    <br></br>\n" +
//			"    <h2>Health and Wellness Resources</h2>\n" +
//			"    <br></br>\n" +
//			"\n" +
//			"\n" +
//			"\n" +
//			"\n" +
//			"\n" +
//			"\n" +
//			"\n" +
//			"\n" +
//			"\n" +
//			"    <!-- insert sleep apnea resources -->\n" +
//			"\n" +
//			"    <!-- End PCP resources -->\n" +
//			"\n" +
//			"<!-- End caregiver health wellness resources -->\n" +
//			"\n" +
//			"    <!-- PCP LIFESKILLS RESOURCES -->\n" +
//			"    <!-- PCP LIFESKILLS RESOURCES -->\n" +
//			"    <!-- PCP LIFESKILLS RESOURCES -->\n" +
//			"\n" +
//			"        <hr></hr>\n" +
//			"        <h2>Life Skills</h2>\n" +
//			"\n" +
//			"\n" +
//			"\n" +
//			"\n" +
//			"        <table class=\"table-checklist\">\n" +
//			"            <tr>\n" +
//			"                <td class=\"td-checkbox\">?</td>\n" +
//			"                <td>\n" +
//			"                    <p>H�s caregiver would like for H to learn how to describe how she is feeling to her doctor.</p>\n" +
//			"                </td>\n" +
//			"            </tr>\n" +
//			"        </table>\n" +
//			"\n" +
//			"\n" +
//			"        <table class=\"table-checklist\">\n" +
//			"            <tr>\n" +
//			"                <td class=\"td-checkbox\">?</td>\n" +
//			"                <td>\n" +
//			"                    <p>H�s caregiver would like for H to learn what each medication is for.</p>\n" +
//			"                </td>\n" +
//			"            </tr>\n" +
//			"        </table>\n" +
//			"\n" +
//			"\n" +
//			"\n" +
//			"\n" +
//			"        <table class=\"table-checklist\">\n" +
//			"            <tr>\n" +
//			"                <td class=\"td-checkbox\">?</td>\n" +
//			"                <td>\n" +
//			"                    <p>H�s caregiver would like for H to learn about the differences between healthy and unhealthy foods.</p>\n" +
//			"                </td>\n" +
//			"            </tr>\n" +
//			"        </table>\n" +
//			"\n" +
//			"        <table class=\"table-checklist\">\n" +
//			"            <tr>\n" +
//			"                <td class=\"td-checkbox\">?</td>\n" +
//			"                <td>\n" +
//			"                    <p>H�s caregiver would like for H to learn about the risks of alcohol, drugs, and tobacco use.</p>\n" +
//			"                </td>\n" +
//			"            </tr>\n" +
//			"        </table>\n" +
//			"\n" +
//			"\n" +
//			"\n" +
//			"        <table class=\"table-checklist\">\n" +
//			"            <tr>\n" +
//			"                <td class=\"td-checkbox\">?</td>\n" +
//			"                <td>\n" +
//			"                    <p>H�s caregiver would like for H to exercise regularly.</p>\n" +
//			"                </td>\n" +
//			"            </tr>\n" +
//			"        </table>\n" +
//			"\n" +
//			"\n" +
//			"\n" +
//			"        <table class=\"table-checklist\">\n" +
//			"            <tr>\n" +
//			"                <td class=\"td-checkbox\">?</td>\n" +
//			"                <td>\n" +
//			"                    <p>H�s caregiver would like for H to learn how to use public transportation on her  own.</p>\n" +
//			"                </td>\n" +
//			"            </tr>\n" +
//			"        </table>\n" +
//			"\n" +
//			"\n" +
//			"\n" +
//			"        <table class=\"table-checklist\">\n" +
//			"            <tr>\n" +
//			"                <td class=\"td-checkbox\">?</td>\n" +
//			"                <td>\n" +
//			"                    <p>H�s caregiver would like for H to understand sexual boundaries and privacy.</p>\n" +
//			"                </td>\n" +
//			"            </tr>\n" +
//			"        </table>\n" +
//			"\n" +
//			"\n" +
//			"\n" +
//			"        <table class=\"table-checklist\">\n" +
//			"            <tr>\n" +
//			"                <td class=\"td-checkbox\">?</td>\n" +
//			"                <td>\n" +
//			"                    <p>H�s caregiver would like for H be able to dress herself.</p>\n" +
//			"                </td>\n" +
//			"            </tr>\n" +
//			"        </table>\n" +
//			"\n" +
//			"\n" +
//			"        <table class=\"table-checklist\">\n" +
//			"            <tr>\n" +
//			"                <td class=\"td-checkbox\">?</td>\n" +
//			"                <td>\n" +
//			"                    <p>H�s caregiver would like for H to prepare her  own meals.</p>\n" +
//			"                </td>\n" +
//			"            </tr>\n" +
//			"        </table>\n" +
//			"\n" +
//			"\n" +
//			"\n" +
//			"        <table class=\"table-checklist\">\n" +
//			"            <tr>\n" +
//			"                <td class=\"td-checkbox\">?</td>\n" +
//			"                <td>\n" +
//			"                    <p>H�s caregiver would like for H to have a plan for what she  will do after finishing high school.</p>\n" +
//			"                </td>\n" +
//			"            </tr>\n" +
//			"        </table>\n" +
//			"\n" +
//			"\n" +
//			"\n" +
//			"        <table class=\"table-checklist\">\n" +
//			"            <tr>\n" +
//			"                <td class=\"td-checkbox\">?</td>\n" +
//			"                <td>\n" +
//			"                    <p>H�s caregiver would like for H to learn where to find her doctor�s phone number.</p>\n" +
//			"                </td>\n" +
//			"            </tr>\n" +
//			"        </table>\n" +
//			"\n" +
//			"        <table class=\"table-checklist\">\n" +
//			"            <tr>\n" +
//			"                <td class=\"td-checkbox\">?</td>\n" +
//			"                <td>\n" +
//			"                    <p>H�s caregiver would like for H to learn how to ask questions of her doctors.</p>\n" +
//			"                </td>\n" +
//			"            </tr>\n" +
//			"        </table>\n" +
//			"\n" +
//			"\n" +
//			"        <table class=\"table-checklist\">\n" +
//			"            <tr>\n" +
//			"                <td class=\"td-checkbox\">?</td>\n" +
//			"                <td>\n" +
//			"                    <p>H�s caregiver would like for H to learn how to find her  medications.</p>\n" +
//			"                </td>\n" +
//			"            </tr>\n" +
//			"        </table>\n" +
//			"\n" +
//			"\n" +
//			"        <table class=\"table-checklist\">\n" +
//			"            <tr>\n" +
//			"                <td class=\"td-checkbox\">?</td>\n" +
//			"                <td>\n" +
//			"                    <p>H�s caregiver would like for H to learn how to take her  medications every day on her own.</p>\n" +
//			"                </td>\n" +
//			"            </tr>\n" +
//			"        </table>\n" +
//			"\n" +
//			"        <table class=\"table-checklist\">\n" +
//			"            <tr>\n" +
//			"                <td class=\"td-checkbox\">?</td>\n" +
//			"                <td>\n" +
//			"                    <p>H�s caregiver would like for H to learn how to refill her  prescriptions on her  own.</p>\n" +
//			"                </td>\n" +
//			"            </tr>\n" +
//			"        </table>\n" +
//			"\n" +
//			"        <table class=\"table-checklist\">\n" +
//			"            <tr>\n" +
//			"                <td class=\"td-checkbox\">?</td>\n" +
//			"                <td>\n" +
//			"                    <p>H�s caregiver would like for H to learn how to swallow whole pills.</p>\n" +
//			"                </td>\n" +
//			"            </tr>\n" +
//			"        </table>\n" +
//			"\n" +
//			"\n" +
//			"\n" +
//			"        <table class=\"table-checklist\">\n" +
//			"            <tr>\n" +
//			"                <td class=\"td-checkbox\">?</td>\n" +
//			"                <td>\n" +
//			"                    <p>H�s caregiver would like for H to learn how to call 911 if there is an emergency.</p>\n" +
//			"                </td>\n" +
//			"            </tr>\n" +
//			"        </table>\n" +
//			"\n" +
//			"        <table class=\"table-checklist\">\n" +
//			"            <tr>\n" +
//			"                <td class=\"td-checkbox\">?</td>\n" +
//			"                <td>\n" +
//			"                    <p>H�s caregiver would like for H to be able to sleep 7 to 8 hours per night.</p>\n" +
//			"                </td>\n" +
//			"            </tr>\n" +
//			"        </table>\n" +
//			"\n" +
//			"\n" +
//			"        <table class=\"table-checklist\">\n" +
//			"            <tr>\n" +
//			"                <td class=\"td-checkbox\">?</td>\n" +
//			"                <td>\n" +
//			"                    <p>H�s caregiver would like for H to learn how to provide her  personal information when needed. </p>\n" +
//			"                </td>\n" +
//			"            </tr>\n" +
//			"        </table>\n" +
//			"\n" +
//			"        <table class=\"table-checklist\">\n" +
//			"            <tr>\n" +
//			"                <td class=\"td-checkbox\">?</td>\n" +
//			"                <td>\n" +
//			"                    <p>H�s caregiver would like for H to learn how to tell the difference between a stranger and a friend.</p>\n" +
//			"                </td>\n" +
//			"            </tr>\n" +
//			"        </table>\n" +
//			"\n" +
//			"\n" +
//			"        <table class=\"table-checklist\">\n" +
//			"            <tr>\n" +
//			"                <td class=\"td-checkbox\">?</td>\n" +
//			"                <td>\n" +
//			"                    <p>H�s caregiver would like for H to learn how to do household chores.</p>\n" +
//			"                </td>\n" +
//			"            </tr>\n" +
//			"        </table>\n" +
//			"\n" +
//			"        <table class=\"table-checklist\">\n" +
//			"            <tr>\n" +
//			"                <td class=\"td-checkbox\">?</td>\n" +
//			"                <td>\n" +
//			"                    <p>H�s caregiver would like for H to learn how to manage her period.</p>\n" +
//			"                </td>\n" +
//			"            </tr>\n" +
//			"        </table>\n" +
//			"\n" +
//			"\n" +
//			"        <table class=\"table-checklist\">\n" +
//			"            <tr>\n" +
//			"                <td class=\"td-checkbox\">?</td>\n" +
//			"                <td>\n" +
//			"                    <p>H�s caregiver would like for H to learn how to brush her  teeth on her  own.</p>\n" +
//			"                </td>\n" +
//			"            </tr>\n" +
//			"        </table>\n" +
//			"\n" +
//			"        <table class=\"table-checklist\">\n" +
//			"            <tr>\n" +
//			"                <td class=\"td-checkbox\">?</td>\n" +
//			"                <td>\n" +
//			"                    <p>H�s caregiver would like for H to be able to use a public restroom on her  own.</p>\n" +
//			"                </td>\n" +
//			"            </tr>\n" +
//			"        </table>\n" +
//			"\n" +
//			"\n" +
//			"        <table class=\"table-checklist\">\n" +
//			"            <tr>\n" +
//			"                <td class=\"td-checkbox\">?</td>\n" +
//			"                <td>\n" +
//			"                    <p>H�s caregiver would like for H to be able to bathe/shower her self.</p>\n" +
//			"                </td>\n" +
//			"            </tr>\n" +
//			"        </table>\n" +
//			"\n" +
//			"\n" +
//			"        <table class=\"table-checklist\">\n" +
//			"            <tr>\n" +
//			"                <td class=\"td-checkbox\">?</td>\n" +
//			"                <td>\n" +
//			"                    <p>H�s caregiver would like for H to be able to do her  own laundry.</p>\n" +
//			"                </td>\n" +
//			"            </tr>\n" +
//			"        </table>\n" +
//			"\n" +
//			"        <table class=\"table-checklist\">\n" +
//			"            <tr>\n" +
//			"                <td class=\"td-checkbox\">?</td>\n" +
//			"                <td>\n" +
//			"                    <p>H�s caregiver would like for H to learn how to find her  insurance card.</p>\n" +
//			"                </td>\n" +
//			"            </tr>\n" +
//			"        </table>\n" +
//			"\n" +
//			"\n" +
//			"\n" +
//			"        <table class=\"table-checklist\">\n" +
//			"            <tr>\n" +
//			"                <td class=\"td-checkbox\">?</td>\n" +
//			"                <td>\n" +
//			"                    <p>H�s caregiver would like for H to name at least two adults she  can ask for help when needed.</p>\n" +
//			"                </td>\n" +
//			"            </tr>\n" +
//			"        </table>\n" +
//			"\n" +
//			"<!-- End pcp lifeskills resources -->\n" +
//			"\n" +
//			"    <!-- PCP CAREGIVER RESOURCES -->\n" +
//			"    <!-- PCP CAREGIVER RESOURCES -->\n" +
//			"    <!-- PCP CAREGIVER RESOURCES -->\n" +
//			"\n" +
//			"    <hr></hr>\n" +
//			"    <h2>Caregiver Information</h2>\n" +
//			"    <br></br>\n" +
//			"\n" +
//			"\n" +
//			"\n" +
//			"\n" +
//			"\n" +
//			"        <table class=\"table-checklist\">\n" +
//			"            <tr>\n" +
//			"                <td class=\"td-checkbox\">?</td>\n" +
//			"                <td>\n" +
//			"                    <p>GiGi�s Playhouse</p>\n" +
//			"                </td>\n" +
//			"            </tr>\n" +
//			"        </table>\n" +
//			"\n" +
//			"        <table class=\"table-checklist\">\n" +
//			"            <tr>\n" +
//			"                <td class=\"td-checkbox\">?</td>\n" +
//			"                <td>\n" +
//			"                    <p>The Global Down Syndrome Foundation</p>\n" +
//			"                </td>\n" +
//			"            </tr>\n" +
//			"        </table>\n" +
//			"\n" +
//			"\n" +
//			"\n" +
//			"        <table class=\"table-checklist\">\n" +
//			"            <tr>\n" +
//			"                <td class=\"td-checkbox\">?</td>\n" +
//			"                <td>\n" +
//			"                    <p>National Down Syndrome Society (NDSS)</p>\n" +
//			"                </td>\n" +
//			"            </tr>\n" +
//			"        </table>\n" +
//			"\n" +
//			"\n" +
//			"\n" +
//			"        <table class=\"table-checklist\">\n" +
//			"            <tr>\n" +
//			"                <td class=\"td-checkbox\">?</td>\n" +
//			"                <td>\n" +
//			"                    <p>We provided a link to specific book pages discussing different guardianship options in the USA</p>\n" +
//			"                </td>\n" +
//			"            </tr>\n" +
//			"        </table>\n" +
//			"\n" +
//			"        <table class=\"table-checklist\">\n" +
//			"            <tr>\n" +
//			"                <td class=\"td-checkbox\">?</td>\n" +
//			"                <td>\n" +
//			"                    <p>We provided a link to resources for adults with disabilities applying for social security</p>\n" +
//			"                </td>\n" +
//			"            </tr>\n" +
//			"        </table>\n" +
//			"\n" +
//			"        <table class=\"table-checklist\">\n" +
//			"            <tr>\n" +
//			"                <td class=\"td-checkbox\">?</td>\n" +
//			"                <td>\n" +
//			"                    <p>We provided resources on setting up a special needs trusts  or an ABLE account</p>\n" +
//			"                </td>\n" +
//			"            </tr>\n" +
//			"        </table>\n" +
//			"\n" +
//			"\n" +
//			"\n" +
//			"<!-- end pcp caregiver info -->\n" +
//			"\n" +
//			"\n" +
//			"\n" +
//			"</div><!-- .pcp-wrapper -->\n" +
//			"\n" +
//			"\n" +
//			"\n" +
//			"\n";


}
