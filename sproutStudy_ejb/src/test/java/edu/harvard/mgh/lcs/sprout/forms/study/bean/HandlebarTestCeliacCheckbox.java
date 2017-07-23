package edu.harvard.mgh.lcs.sprout.forms.study.bean;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.jknack.handlebars.*;
import com.github.jknack.handlebars.context.FieldValueResolver;
import com.github.jknack.handlebars.context.JavaBeanValueResolver;
import com.github.jknack.handlebars.context.MapValueResolver;
import com.github.jknack.handlebars.context.MethodValueResolver;
import edu.harvard.mgh.lcs.sprout.forms.transform.handlebars.Helpers;
import org.junit.Test;

import java.io.*;
import java.security.SecureRandom;

public class HandlebarTestCeliacCheckbox {

	 private SecureRandom random = new SecureRandom();

	private String template = new String();


	@Test
	public void test() {

		init();
		try {

			Handlebars handlebars = new Handlebars();

//			handlebars.registerHelper("compare", , function (context) {
//				return 'Hello ' + context;
//			})


//			InputStream inputStream = this.getClass().getResourceAsStream("/edu/harvard/mgh/lcs/sprout/forms/transform/handlebars/helpers/compare.js");


//			String f = getStringFromInputStream(inputStream);
//			System.out.println("f = " + f);

			handlebars.registerHelpers(new Helpers());



			String json = "{\n" +
					"  \"sprout\": {\n" +
					"    \"userId\": \"1@sprouttransform\",\n" +
					"    \"inboxId\": \"11777\",\n" +
					"    \"instanceId\": \"FA9633B4-9739-44DC-9ABA-12F85204A2B9\",\n" +
					"    \"currentStep\": \"step0\",\n" +
					"    \"currentInput\": \"\",\n" +
					"    \"validationStatus\": \"\",\n" +
					"    \"initStep\": \"\",\n" +
					"    \"initInput\": \"\",\n" +
					"    \"submissionDate\": \"1500672585232\",\n" +
					"    \"expirationDate\": \"\",\n" +
					"    \"lockKey\": \"F972AB4E-489A-44C5-B273-36F668BBCD6C\",\n" +
					"    \"locale\": \"en\",\n" +
					"    \"custom\": {\n" +
					"      \"mode\": \"caregiver\"\n" +
					"    }\n" +
					"  },\n" +
					"  \"form\": {\n" +
					"    \"id\": \"487\",\n" +
					"    \"version\": \"7\",\n" +
					"    \"mode\": \"\"\n" +
					"  },\n" +
					"  \"pcori_dsp_interventi\": {\n" +
					"    \"first_name\": \"H\",\n" +
					"    \"last_name\": \"Test\",\n" +
					"    \"pt_dob\": \"01/01/1980\",\n" +
					"    \"pt_gender\": {\n" +
					"      \"name\": \"\",\n" +
					"      \"value\": \"F\"\n" +
					"    },\n" +
					"    \"pt_age\": \"37\",\n" +
					"    \"heshe\": \"she\",\n" +
					"    \"hishertheir\": \"her\",\n" +
					"    \"himherthem\": \"her\",\n" +
					"    \"ds_dx1\": {\n" +
					"      \"name\": \"Trisomy 21\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"ds_dx2\": {\n" +
					"      \"name\": \"Mosaic Down syndrome\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"ds_dx3\": {\n" +
					"      \"name\": \"Translocation Down syndrome\",\n" +
					"      \"value\": \"1\"\n" +
					"    },\n" +
					"    \"ds_dx4\": {\n" +
					"      \"name\": \"Not Sure\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"further_info\": {\n" +
					"      \"name\": \"\",\n" +
					"      \"value\": \"0\"\n" +
					"    },\n" +
					"    \"pcp_name_title\": {\n" +
					"      \"name\": \"Mr.\",\n" +
					"      \"value\": \"mr\"\n" +
					"    },\n" +
					"    \"pcp_name\": \"Shaw\",\n" +
					"    \"fever\": {\n" +
					"      \"name\": \"Fever that hasn't gone away in at least five days\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"weight_gain\": {\n" +
					"      \"name\": \"Weight gain\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"weight_loss\": {\n" +
					"      \"name\": \"Undesired weight loss\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"limp\": {\n" +
					"      \"name\": \"New limp\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"ftt\": {\n" +
					"      \"name\": \"Unexplained growth failure\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"increasing_fatigue\": {\n" +
					"      \"name\": \"Increasing fatigue\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"concerns_about_vision\": {\n" +
					"      \"name\": \"Vision problems\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"involuntary_eye_movements\": {\n" +
					"      \"name\": \"Involuntary eye movements\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"hearing_loss\": {\n" +
					"      \"name\": \"Hearing loss\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"runny_nose\": {\n" +
					"      \"name\": \"Frequent runny nose\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"sinus_problems\": {\n" +
					"      \"name\": \"Sinus problems\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"gum_problems\": {\n" +
					"      \"name\": \"Gum problems\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"cavities\": {\n" +
					"      \"name\": \"Cavities\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"brushing\": {\n" +
					"      \"name\": \"Difficulty brushing teeth\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"flossing\": {\n" +
					"      \"name\": \"Difficulty flossing\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"murmur\": {\n" +
					"      \"name\": \"A new murmur noticed by a doctor\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"poor_exercise_tolerance\": {\n" +
					"      \"name\": \"Becoming increasingly tired after exercise\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"stool\": {\n" +
					"      \"name\": \"Bulky or foul-smelling stools\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"vomiting\": {\n" +
					"      \"name\": \"Vomiting\",\n" +
					"      \"value\": \"1\"\n" +
					"    },\n" +
					"    \"nausea\": {\n" +
					"      \"name\": \"Nausea\",\n" +
					"      \"value\": \"1\"\n" +
					"    },\n" +
					"    \"incontinence_stool\": {\n" +
					"      \"name\": \"New accidents with stool\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"reflux\": {\n" +
					"      \"name\": \"Heartburn\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"constipation\": {\n" +
					"      \"name\": \"Constipation\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"diarrhea\": {\n" +
					"      \"name\": \"Diarrhea\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"flatulence\": {\n" +
					"      \"name\": \"Passing esxcessive gas\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"bloating\": {\n" +
					"      \"name\": \"Bloating\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"frequent_urination\": {\n" +
					"      \"name\": \"Frequent urination\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"toilet_training\": {\n" +
					"      \"name\": \"Toilet training\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"cryptorchidism\": {\n" +
					"      \"name\": \"Testes that have not come down into the scrotum (the sac of skin underneath the penis)\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"accidents_urine\": {\n" +
					"      \"name\": \"New accidents with urine\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"irregular_menstruation\": {\n" +
					"      \"name\": \"Irregular periods\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"menstrual_cramps\": {\n" +
					"      \"name\": \"Severe cramps\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"ocp\": {\n" +
					"      \"name\": \"Questions about birth control pills\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"sex_active\": {\n" +
					"      \"name\": \"Questions about dating or social boundaries\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"concerns_puberty\": {\n" +
					"      \"name\": \"Concerns about body development (puberty)\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"masturbation\": {\n" +
					"      \"name\": \"Concerns or questions about masturbation\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"dry_skin\": {\n" +
					"      \"name\": \"Dry skin\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"vitiligo\": {\n" +
					"      \"name\": \"Areas of skin that have turned white or lost their color\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"acne\": {\n" +
					"      \"name\": \"Acne\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"eczema\": {\n" +
					"      \"name\": \"Eczema\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"hair_loss\": {\n" +
					"      \"name\": \"Hair loss\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"hidradenitis\": {\n" +
					"      \"name\": \"Skin boils under armpits and groin\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"itching\": {\n" +
					"      \"name\": \"Itching\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"onychomycosis_current\": {\n" +
					"      \"name\": \"Toenails that have turned yellow, white or brown, or changed shape\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"torticollis\": {\n" +
					"      \"name\": \"Head stays tilted to one side\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"arms_hands\": {\n" +
					"      \"name\": \"Change in how she uses arms or hands\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"neck_pain\": {\n" +
					"      \"name\": \"Neck pain\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"walk_change\": {\n" +
					"      \"name\": \"Changes in how she walks\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"joint_stiffness\": {\n" +
					"      \"name\": \"Joint stiffness\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"joint_pain\": {\n" +
					"      \"name\": \"Joint pain\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"joint_swelling\": {\n" +
					"      \"name\": \"Joint swelling\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"joint_redness\": {\n" +
					"      \"name\": \"Joint redness\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"new_weakness\": {\n" +
					"      \"name\": \"New weakness in one or more limbs\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"excessive_thirst\": {\n" +
					"      \"name\": \"Excessive thirst\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"too_hot\": {\n" +
					"      \"name\": \"Feeling too hot\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"too_cold\": {\n" +
					"      \"name\": \"Feeling too cold\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"delayed_puberty\": {\n" +
					"      \"name\": \"Short stature compared with other children with Down syndrome [hishertheir] age\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"seasonal_allergies\": {\n" +
					"      \"name\": \"Seasonal allergies\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"infantile_spasms\": {\n" +
					"      \"name\": \"Abnormal facial grimaces\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"infantile_spasms2\": {\n" +
					"      \"name\": \"Jerking of arms or legs\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"infantile_spasms3\": {\n" +
					"      \"name\": \"Staring episodes\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"seizures\": {\n" +
					"      \"name\": \"Uncontrollable shaking of arms and legs\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"ability_change\": {\n" +
					"      \"name\": \"Decrease in activity or overall functioning\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"snore\": {\n" +
					"      \"name\": \"Snoring\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"gasp_sleep\": {\n" +
					"      \"name\": \"Gasping, snorting, choking during sleep\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"mouth_open\": {\n" +
					"      \"name\": \"Breathing with open mouth during sleep\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"sleep_walk\": {\n" +
					"      \"name\": \"Night terrors, sleep walk, sleep talk\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"wet_bed\": {\n" +
					"      \"name\": \"Wetting the bed\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"multiple_pillows\": {\n" +
					"      \"name\": \"Sleeping on multiple pillows\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"positions\": {\n" +
					"      \"name\": \"Sleeping in strange positions\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"refresh_after_sleep\": {\n" +
					"      \"name\": \"Not refreshed despite adequate sleep\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"fall_asleep_short_drive\": {\n" +
					"      \"name\": \"Falling asleep in car on short drive\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"asleep_school\": {\n" +
					"      \"name\": \"Falling asleep/napping at school\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"hidden_celiac1\": \"1\",\n" +
					"    \"sad_depression\": {\n" +
					"      \"name\": \"Feeling down, depressed, or hopeless\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"lack_interest_activities\": {\n" +
					"      \"name\": \"Little interest or pleasure in doing things\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"cries_easily\": {\n" +
					"      \"name\": \"Cries easily for no reason\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"moves_slow\": {\n" +
					"      \"name\": \"Moves slowly\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"lose_skills\": {\n" +
					"      \"name\": \"Loss of previously learned skills\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"eat\": {\n" +
					"      \"name\": \"Eating too much or eating too little\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"sleep\": {\n" +
					"      \"name\": \"Trouble falling or staying asleep\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"tense_anxiety\": {\n" +
					"      \"name\": \"Tense, anxious, worried\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"alone_distress\": {\n" +
					"      \"name\": \"Distressed about being alone\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"nonverbal\": {\n" +
					"      \"name\": \"Nonverbal or significant expressive language challenges\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"distress_separate\": {\n" +
					"      \"name\": \"Distressed when separated from familiar person\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"refuses_school\": {\n" +
					"      \"name\": \"Refuses to go to school or activity\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"peers\": {\n" +
					"      \"name\": \"Cannot keep up with age-matched peers with Down syndrome\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"ritualistic_behaviors_ocd\": {\n" +
					"      \"name\": \"Ritualistic compulsive behaviors (e.g., checking things/cleaning/grooming)\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"obsessive_thoughts\": {\n" +
					"      \"name\": \"Gets obsessed with idea or activity\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"aloof_asd\": {\n" +
					"      \"name\": \"Distant or aloof, in her own world\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"do_own\": {\n" +
					"      \"name\": \"Prefers to do things on her own\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"not_respond_feelings\": {\n" +
					"      \"name\": \"Doesn't respond to others' feelings\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"arranges_objects\": {\n" +
					"      \"name\": \"Arranges objects in a strict order\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"upset_changes_routine\": {\n" +
					"      \"name\": \"Gets upset over small changes in routine\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"repetitive_activity\": {\n" +
					"      \"name\": \"Stimming (such as rocking, pacing, or hand-flapping)\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"talk_self\": {\n" +
					"      \"name\": \"Talks to self or imaginary people\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"groove\": {\n" +
					"      \"name\": \"Tendency toward repetitive behaviors, rigitidy, or sameness\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"avoids_eye\": {\n" +
					"      \"name\": \"Avoids eye contact\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"sensory_objects\": {\n" +
					"      \"name\": \"Smells, tastes or licks objects\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"hyperactivity_adhd\": {\n" +
					"      \"name\": \"Overactive, restless, unable to sit still\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"inattention\": {\n" +
					"      \"name\": \"Inattention\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"impulsivity\": {\n" +
					"      \"name\": \"Impulsive, acts without thinking\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"distracted\": {\n" +
					"      \"name\": \"Easily distracted\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"uncooperative\": {\n" +
					"      \"name\": \"Uncooperative, disobeys\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"temper_meltdowns_irritability\": {\n" +
					"      \"name\": \"Temper tantrums or outburst or meltdowns\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"throws_objects\": {\n" +
					"      \"name\": \"Throws or breaks objects\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"hits_self\": {\n" +
					"      \"name\": \"Hits self\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"bites_self\": {\n" +
					"      \"name\": \"Bites self\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"injures_self\": {\n" +
					"      \"name\": \"Hurts herself on purpose (e.g. hitting head, biting hands)\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"bangs_head\": {\n" +
					"      \"name\": \"Bangs head\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"irritable\": {\n" +
					"      \"name\": \"Irritability\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"hits_others\": {\n" +
					"      \"name\": \"Kicks or hits others\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"mood_swings\": {\n" +
					"      \"name\": \"Mood changes rapidly for no reason\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"unreal_happy\": {\n" +
					"      \"name\": \"Unrealistically happy\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"aggression\": {\n" +
					"      \"name\": \"Aggression\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"property_destruction\": {\n" +
					"      \"name\": \"Property destruction (e.g. Breaks things, hits walls, throws things)\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"forgetful\": {\n" +
					"      \"name\": \"Forgetful\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"situation_fear\": {\n" +
					"      \"name\": \"Fears particular situations (e.g. dark, animals)\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"stereotypic_movements\": {\n" +
					"      \"name\": \"Stereotypic motor movements (e.g., hand flapping)\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"chews_parts\": {\n" +
					"      \"name\": \"Chews body parts\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"agitation\": {\n" +
					"      \"name\": \"General agitation\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"function_adl\": {\n" +
					"      \"name\": \"Change in the ability to perform activities of daily living (such as washing/bathing, ability to dress self, ability to feed self etc.)\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"word_difficulty\": {\n" +
					"      \"name\": \"New word finding difficulties (cannot remember words that she once knew)\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"wandering\": {\n" +
					"      \"name\": \"Wandering (leaves the house and gets lost walking around the community; wandering without a clear purpose\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"confusion_night\": {\n" +
					"      \"name\": \"Confusion at night\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"undress_public\": {\n" +
					"      \"name\": \"Undresses inappropriately (for example in public)\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"recognition\": {\n" +
					"      \"name\": \"Does not recognize familiar persons\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"lost_dementia\": {\n" +
					"      \"name\": \"Gets lost in familiar surroundings\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"muddled_dementia\": {\n" +
					"      \"name\": \"Often puts familiar things in wrong places\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"objects_familiar_dementia\": {\n" +
					"      \"name\": \"Does not know what to do with familiar objects\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"hidden_celiac2\": \"0\",\n" +
					"    \"dentist\": {\n" +
					"      \"name\": \"\",\n" +
					"      \"value\": \"0\"\n" +
					"    },\n" +
					"    \"thyroid_test\": {\n" +
					"      \"name\": \"\",\n" +
					"      \"value\": \"0\"\n" +
					"    },\n" +
					"    \"vfss\": {\n" +
					"      \"name\": \"\",\n" +
					"      \"value\": \"0\"\n" +
					"    },\n" +
					"    \"sleep_study\": {\n" +
					"      \"name\": \"\",\n" +
					"      \"value\": \"0\"\n" +
					"    },\n" +
					"    \"celiac_test\": {\n" +
					"      \"name\": \"\",\n" +
					"      \"value\": \"999\"\n" +
					"    },\n" +
					"    \"cspine\": {\n" +
					"      \"name\": \"\",\n" +
					"      \"value\": \"0\"\n" +
					"    },\n" +
					"    \"keratoconus\": {\n" +
					"      \"name\": \"Keratoconus\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"strabismus\": {\n" +
					"      \"name\": \"Strabismus or Extropia\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"astigmatism\": {\n" +
					"      \"name\": \"Astigmatism\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"nystagmus\": {\n" +
					"      \"name\": \"Nystagmus\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"cataracts\": {\n" +
					"      \"name\": \"Cataracts\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"pmh_hearing\": {\n" +
					"      \"name\": \"Hearing loss\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"pmh_otitis\": {\n" +
					"      \"name\": \"Chronic ear infections\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"asd\": {\n" +
					"      \"name\": \"Atrial Septal Defect\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"vsd\": {\n" +
					"      \"name\": \"Ventricular Septal Defect\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"avsd\": {\n" +
					"      \"name\": \"Atrioventricular septal defect\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"pda\": {\n" +
					"      \"name\": \"Patent Ductus Arteriosus\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"tof\": {\n" +
					"      \"name\": \"Tetralogy of Fallot\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"arrythmia\": {\n" +
					"      \"name\": \"Arrythmia\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"heart_nos\": {\n" +
					"      \"name\": \"Other Heart Defect\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"hyperlipidemia\": {\n" +
					"      \"name\": \"High cholesterol\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"pneumonia\": {\n" +
					"      \"name\": \"Pneumonias\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"osa\": {\n" +
					"      \"name\": \"Obstructive Sleep Apnea\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"asthma\": {\n" +
					"      \"name\": \"Asthma\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"constipation2\": {\n" +
					"      \"name\": \"Chronic constipation\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"duodenal_atresia\": {\n" +
					"      \"name\": \"Duodenal atresia\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"gerd1\": {\n" +
					"      \"name\": \"Gastroesophageal reflux disease\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"aspiration1\": {\n" +
					"      \"name\": \"Aspiration\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"celiacd\": {\n" +
					"      \"name\": \"Celiac disease\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"hirschsprung\": {\n" +
					"      \"name\": \"Hirschsprung Disease\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"pmh_lactose\": {\n" +
					"      \"name\": \"Lactose intolerance\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"pmh_aus\": {\n" +
					"      \"name\": \"Imperforate anus\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"pmh_tef\": {\n" +
					"      \"name\": \"Transesophageal fistula\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"pmh_obesity\": {\n" +
					"      \"name\": \"Overweight/obesity\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"pmh_incontinence\": {\n" +
					"      \"name\": \"Urinary incontinence\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"pmh_seizures\": {\n" +
					"      \"name\": \"Seizures\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"pmh_dementia\": {\n" +
					"      \"name\": \"Dementia (Alzheimer's disease)\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"pmh_autism\": {\n" +
					"      \"name\": \"Autism Spectrum Disorder (ASD)\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"pmh_hypotonia\": {\n" +
					"      \"name\": \"Hypotonia (poor muscle tone)\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"pmh_eczema\": {\n" +
					"      \"name\": \"Eczema\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"pmh_folliculitis\": {\n" +
					"      \"name\": \"Folliculitis\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"pmh_hidraedenitis\": {\n" +
					"      \"name\": \"Hidradenitis Suppurativa (skin boils)\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"pmh_seborrheicdermatitits\": {\n" +
					"      \"name\": \"Seborrheic dermatitis\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"pmh_xerosis\": {\n" +
					"      \"name\": \"Xerosis\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"pmh_toenailfungus\": {\n" +
					"      \"name\": \"Onchyomycosis\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"pmh_hairloss\": {\n" +
					"      \"name\": \"Alopecia areata\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"pmh_vitiligo\": {\n" +
					"      \"name\": \"Vitiligo\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"pmh_scoliosis\": {\n" +
					"      \"name\": \"Scoliosis\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"pmh_aai\": {\n" +
					"      \"name\": \"Atlantoaxial instability\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"pmh_arthritis\": {\n" +
					"      \"name\": \"Arthritis\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"pmh_osteoporosis\": {\n" +
					"      \"name\": \"Osteoporosis\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"pmh_hypothyroidism\": {\n" +
					"      \"name\": \"Hypothyroidism\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"pmh_hyperthyroidism\": {\n" +
					"      \"name\": \"Hyperthyroidism\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"pmh_hashimotos\": {\n" +
					"      \"name\": \"Hashimoto's thyroiditis\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"pmh_graves\": {\n" +
					"      \"name\": \"Graves disease\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"pmh_diabetes\": {\n" +
					"      \"name\": \"Diabetes mellitus\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"pmh_all\": {\n" +
					"      \"name\": \"Acute Lymphoblastic Leukemia (ALL)\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"pmh_aml\": {\n" +
					"      \"name\": \"Acute Myelogenous Leukemia (AML)\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"pmh_tmpd\": {\n" +
					"      \"name\": \"Transient myeloproliferative disorder\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"testicular_pmh\": {\n" +
					"      \"name\": \"Testicular cancer\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"pmh_anemia\": {\n" +
					"      \"name\": \"Anemia\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"pmh_depression\": {\n" +
					"      \"name\": \"Depression\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"pmh_anxiety\": {\n" +
					"      \"name\": \"Anxiety\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"pmh_ocd\": {\n" +
					"      \"name\": \"Obsessive Compulsive Disorder (OCD)\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"pmh_adhd\": {\n" +
					"      \"name\": \"Attention deficit hyperactvity disorder (ADHD)\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"pmh_ptsd\": {\n" +
					"      \"name\": \"Post-traumatic stress disorder (PTSD)\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"imm_influenza\": {\n" +
					"      \"name\": \"\",\n" +
					"      \"value\": \"5\"\n" +
					"    },\n" +
					"    \"imm_pneumonia\": {\n" +
					"      \"name\": \"\",\n" +
					"      \"value\": \"999\"\n" +
					"    },\n" +
					"    \"beh4\": {\n" +
					"      \"name\": \"Rigid sensory preferences (picky eating)\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"beh5\": {\n" +
					"      \"name\": \"Refusal to eat\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"beh6\": {\n" +
					"      \"name\": \"Binge eating\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"beh7\": {\n" +
					"      \"name\": \"Excessive snacking\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"conc_pockets\": {\n" +
					"      \"name\": \"Pockets food, meaning she leaves food in her mouth and does not swallow right away.\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"conc_chew\": {\n" +
					"      \"name\": \"H does not chew her food well\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"conc_toofast\": {\n" +
					"      \"name\": \"Eats too fast or puts too much food in her  mouth at one time.\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"conc_cough\": {\n" +
					"      \"name\": \"Coughs when she eats solid food\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"conc_texture\": {\n" +
					"      \"name\": \"H is particular with the texture of certain foods\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"sign_language\": {\n" +
					"      \"name\": \"Using sign language to communicate\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"speech_comm\": {\n" +
					"      \"name\": \"Speech and Communication skills\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"speech_apraxia\": {\n" +
					"      \"name\": \"Speech Apraxia\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"augmentative_comm\": {\n" +
					"      \"name\": \"Augmentative and Alternative Communication Devices\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"gross_motor\": {\n" +
					"      \"name\": \"Gross motor skills\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"fine_motor\": {\n" +
					"      \"name\": \"Fine motor skills\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"sensory_processing\": {\n" +
					"      \"name\": \"Sensory processing concerns\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"aba_therapy\": {\n" +
					"      \"name\": \"Applied Behavioral Analysis (ABA) Therapy\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"math\": {\n" +
					"      \"name\": \"Math skills\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"reading\": {\n" +
					"      \"name\": \"Reading skills\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"fba\": {\n" +
					"      \"name\": \"Functional behavioral asessment\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"classroom_type\": {\n" +
					"      \"name\": \"Types of Classroom Placements\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"inclusion\": {\n" +
					"      \"name\": \"Suggestions for meaningful classroom inclusion\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"special_edu_law\": {\n" +
					"      \"name\": \"Special education law\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"edu_advocacy\": {\n" +
					"      \"name\": \"Educational advocacy\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"iep_504\": {\n" +
					"      \"name\": \"Differences between IEP v. 504\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"vocation\": {\n" +
					"      \"name\": \"Vocational skills/Employment Opportunities\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"adaptive_living\": {\n" +
					"      \"name\": \"Adaptive living skills\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"raising_alone\": {\n" +
					"      \"name\": \"Raising a child with Down syndrome alone, whether by choice or circumstance\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"sibling_resources\": {\n" +
					"      \"name\": \"Supports and resources for brothers/sisters\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"moving_out\": {\n" +
					"      \"name\": \"Moving out of the House/Residential Options\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"brain_donation\": {\n" +
					"      \"name\": \"Information about brain donation\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"guardianship\": {\n" +
					"      \"name\": \"Information about guardianship options\",\n" +
					"      \"value\": \"1\"\n" +
					"    },\n" +
					"    \"ssi_2\": {\n" +
					"      \"name\": \"Information about Social Security Insurance?\",\n" +
					"      \"value\": \"1\"\n" +
					"    },\n" +
					"    \"able_2\": {\n" +
					"      \"name\": \"More information about financial planning, special needs trusts, or ABLE accounts?\",\n" +
					"      \"value\": \"1\"\n" +
					"    },\n" +
					"    \"gen_md_phone\": {\n" +
					"      \"name\": \"\",\n" +
					"      \"value\": \"1\"\n" +
					"    },\n" +
					"    \"gen_md_talk\": {\n" +
					"      \"name\": \"\",\n" +
					"      \"value\": \"1\"\n" +
					"    },\n" +
					"    \"gen_sx_describe\": {\n" +
					"      \"name\": \"\",\n" +
					"      \"value\": \"2\"\n" +
					"    },\n" +
					"    \"gen_meds\": {\n" +
					"      \"name\": \"\",\n" +
					"      \"value\": \"1\"\n" +
					"    },\n" +
					"    \"gen_meds_reason\": {\n" +
					"      \"name\": \"\",\n" +
					"      \"value\": \"2\"\n" +
					"    },\n" +
					"    \"gen_meds_take\": {\n" +
					"      \"name\": \"\",\n" +
					"      \"value\": \"1\"\n" +
					"    },\n" +
					"    \"gen_rx\": {\n" +
					"      \"name\": \"\",\n" +
					"      \"value\": \"1\"\n" +
					"    },\n" +
					"    \"gen_meds_swallow\": {\n" +
					"      \"name\": \"\",\n" +
					"      \"value\": \"1\"\n" +
					"    },\n" +
					"    \"well_foods\": {\n" +
					"      \"name\": \"\",\n" +
					"      \"value\": \"2\"\n" +
					"    },\n" +
					"    \"well_habits\": {\n" +
					"      \"name\": \"\",\n" +
					"      \"value\": \"2\"\n" +
					"    },\n" +
					"    \"well_911\": {\n" +
					"      \"name\": \"\",\n" +
					"      \"value\": \"1\"\n" +
					"    },\n" +
					"    \"well_sleep\": {\n" +
					"      \"name\": \"\",\n" +
					"      \"value\": \"1\"\n" +
					"    },\n" +
					"    \"well_exercise\": {\n" +
					"      \"name\": \"\",\n" +
					"      \"value\": \"2\"\n" +
					"    },\n" +
					"    \"gen_id\": {\n" +
					"      \"name\": \"\",\n" +
					"      \"value\": \"1\"\n" +
					"    },\n" +
					"    \"gen_stranger\": {\n" +
					"      \"name\": \"\",\n" +
					"      \"value\": \"1\"\n" +
					"    },\n" +
					"    \"self_public_transport\": {\n" +
					"      \"name\": \"\",\n" +
					"      \"value\": \"2\"\n" +
					"    },\n" +
					"    \"self_chores\": {\n" +
					"      \"name\": \"\",\n" +
					"      \"value\": \"1\"\n" +
					"    },\n" +
					"    \"self_period\": {\n" +
					"      \"name\": \"\",\n" +
					"      \"value\": \"1\"\n" +
					"    },\n" +
					"    \"self_privacy\": {\n" +
					"      \"name\": \"\",\n" +
					"      \"value\": \"2\"\n" +
					"    },\n" +
					"    \"self_dentist\": {\n" +
					"      \"name\": \"\",\n" +
					"      \"value\": \"1\"\n" +
					"    },\n" +
					"    \"self_restroom\": {\n" +
					"      \"name\": \"\",\n" +
					"      \"value\": \"1\"\n" +
					"    },\n" +
					"    \"self_dress\": {\n" +
					"      \"name\": \"\",\n" +
					"      \"value\": \"2\"\n" +
					"    },\n" +
					"    \"self_bathe\": {\n" +
					"      \"name\": \"\",\n" +
					"      \"value\": \"1\"\n" +
					"    },\n" +
					"    \"self_meals\": {\n" +
					"      \"name\": \"\",\n" +
					"      \"value\": \"2\"\n" +
					"    },\n" +
					"    \"self_laundry\": {\n" +
					"      \"name\": \"\",\n" +
					"      \"value\": \"1\"\n" +
					"    },\n" +
					"    \"know_insurance\": {\n" +
					"      \"name\": \"\",\n" +
					"      \"value\": \"1\"\n" +
					"    },\n" +
					"    \"know_plan\": {\n" +
					"      \"name\": \"\",\n" +
					"      \"value\": \"2\"\n" +
					"    },\n" +
					"    \"know_adults\": {\n" +
					"      \"name\": \"\",\n" +
					"      \"value\": \"1\"\n" +
					"    },\n" +
					"    \"adrc\": {\n" +
					"      \"name\": \"ADRC\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"arc\": {\n" +
					"      \"name\": \"The Arc\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"bestbuds\": {\n" +
					"      \"name\": \"Best Buddies\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"dsconnect\": {\n" +
					"      \"name\": \"DS-Connect®\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"gigi\": {\n" +
					"      \"name\": \"GiGi's Playhouse\",\n" +
					"      \"value\": \"1\"\n" +
					"    },\n" +
					"    \"gdsf\": {\n" +
					"      \"name\": \"The Global Down Syndrome Foundation\",\n" +
					"      \"value\": \"1\"\n" +
					"    },\n" +
					"    \"lumind\": {\n" +
					"      \"name\": \"LuMind Research Down Syndrome Foundation\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"ndsc\": {\n" +
					"      \"name\": \"National Down Syndrome Congress (NDSC)\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"ndss\": {\n" +
					"      \"name\": \"National Down Syndrome Society (NDSS)\",\n" +
					"      \"value\": \"1\"\n" +
					"    },\n" +
					"    \"specialolympics\": {\n" +
					"      \"name\": \"Special Olympics\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"hidden_autism_helper1\": \"0\",\n" +
					"    \"hidden_autism_helper2\": \"0\",\n" +
					"    \"hidden_autism\": \"0\",\n" +
					"    \"hidden_disruptivebehavior\": \"0\",\n" +
					"    \"hidden_adhd\": \"0\",\n" +
					"    \"hidden_cspine\": \"0\",\n" +
					"    \"hidden_arthritis\": \"0\",\n" +
					"    \"hidden_aspiration\": \"0\",\n" +
					"    \"hidden_anxiety_helper\": \"0\",\n" +
					"    \"hidden_anxiety\": \"0\",\n" +
					"    \"hidden_dementia_helper1\": \"0\",\n" +
					"    \"hidden_dementia_helper2\": \"0\",\n" +
					"    \"hidden_dementia\": \"0\",\n" +
					"    \"hidden_depression_helper1\": \"0\",\n" +
					"    \"hidden_depression_helper2\": \"0\",\n" +
					"    \"hidden_depression_helper3\": \"0\",\n" +
					"    \"hidden_depression_helper4\": \"0\",\n" +
					"    \"hidden_depression\": \"0\"\n" +
					"  },\n" +
					"  \"translations\": {\n" +
					"    \"test\": {\n" +
					"      \"en\": \"test\",\n" +
					"      \"es\": \"prueba\"\n" +
					"    },\n" +
					"    \"test2\": {\n" +
					"      \"en\": \"test\",\n" +
					"      \"es\": \"Español\"\n" +
					"    },\n" +
					"    \"test3\": {\n" +
					"      \"en\": \"test\",\n" +
					"      \"es\": \"Español\"\n" +
					"    },\n" +
					"    \"checkmark\": {\n" +
					"      \"en\": \"<img src=\\\"https://sprouttest.partners.org/sproutassets/brands/pcori-dsp/images/checkbox.png\\\" class=\\\"img-checkbox\\\"></img>\",\n" +
					"      \"es\": \"<img src=\\\"https://sprouttest.partners.org/sproutassets/brands/pcori-dsp/images/checkbox.png\\\" class=\\\"img-checkbox\\\"></img>\"\n" +
					"    },\n" +
					"    \"linkout\": {\n" +
					"      \"en\": \"<img src=\\\"https://sprouttest.partners.org/sproutassets/brands/pcori-dsp/images/externallink.png\\\" class=\\\"img-externallink\\\"></img>\",\n" +
					"      \"es\": \"<img src=\\\"https://sprouttest.partners.org/sproutassets/brands/pcori-dsp/images/externallink.png\\\" class=\\\"img-externallink\\\"></img>\"\n" +
					"    },\n" +
					"    \"why\": {\n" +
					"      \"en\": \"Why?\",\n" +
					"      \"es\": \"¿Por qué?\"\n" +
					"    },\n" +
					"    \"guardianship\": {\n" +
					"      \"en\": \"<strong>Guardianship options.</strong> Guardianship laws can vary from state to state. However,  there are some general guardianship options you might consider for {{pcori_dsp_interventi.first_name}}. Check out pages 92-100 in <a href=\\\"https://www.woodbinehouse.com/contents.asp?product_id=978-1-890627-87-4\\\" target=\\\"_blank\\\">this book {{#i18n \\\"linkout\\\"}}{{/i18n}}</a> for information on guardianship options in the USA.\",\n" +
					"      \"es\": \"\"\n" +
					"    },\n" +
					"    \"guardianship_why\": {\n" +
					"      \"en\": \"You indicated that you would like more information on guardianship options.\",\n" +
					"      \"es\": \"\"\n" +
					"    }\n" +
					"  }\n" +
					"}";
			JsonNode jsonNode = new ObjectMapper().readValue(json, JsonNode.class);
//			Handlebars handlebars = new Handlebars();
			handlebars.registerHelper("json", Jackson2Helper.INSTANCE);
//			handlebars.registerHelpers("compare", inputStream);

			Context context = Context
					.newBuilder(jsonNode)
					.resolver(JsonNodeValueResolver.INSTANCE,
							JavaBeanValueResolver.INSTANCE,
							FieldValueResolver.INSTANCE,
							MapValueResolver.INSTANCE,
							MethodValueResolver.INSTANCE
					)
					.build();
			Template template2 = handlebars.compileInline(template.toString());
			System.out.println(template2.apply(context));




		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	// convert InputStream to String
	private static String getStringFromInputStream(InputStream is) {

		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();

		String line;
		try {

			br = new BufferedReader(new InputStreamReader(is));
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return sb.toString();

	}
	private void init() {
		InputStream is = null;
		try {
			is = new FileInputStream("/Users/slorenz/Desktop/template.txt");
			BufferedReader buf = new BufferedReader(new InputStreamReader(is));
			String line = buf.readLine();
			StringBuilder sb = new StringBuilder();
			while (line != null) {
				sb.append(line).append("\n");
				line = buf.readLine();
			}

			template = sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}


	}
}
