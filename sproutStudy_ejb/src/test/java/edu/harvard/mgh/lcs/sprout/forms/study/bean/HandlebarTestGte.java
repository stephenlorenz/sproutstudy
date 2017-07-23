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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.SecureRandom;

public class HandlebarTestGte {

	 private SecureRandom random = new SecureRandom();

	private StringBuilder template = new StringBuilder();


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


		template.append("<head>\n" +
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
				"{{#compare sprout.custom.mode \"==\" \"caregiver\"}}\n" +
				"\n" +
				"\n" +
				"<div class=\"caregiver-wrapper\">\n" +
				"\n" +
				"    <h1>Personalized Checklist for {{pcori_dsp_interventi.first_name}}'s Caregiver</h1>\n" +
				"\n" +
				"    <p>Your responses from Down Syndrome Clinic to You (DSC2U) were used to create this personalized checklist for {{pcori_dsp_interventi.first_name}}.  Click on \"<strong class=\"why\">Why?</strong>\" after each recommendation if you are interested in knowing why this suggestion was made for {{pcori_dsp_interventi.first_name}}. All explanations will appear automatically when you download a copy of this plan.</p>\n" +
				"\n" +
				"    <p>This checklist does not establish a health care provider-patient relationship. It is not an attempt to practice medicine or provide specific clinical advice. It is intended to provide useful information to you and {{pcori_dsp_interventi.first_name}}'s doctor for reference and educational purposes only. </p>\n" +
				"\n" +
				"    <p>This checklist contains computer-generated suggestions based on your answers to the DSC2U survey. It was not prepared or reviewed by a clinician specifically for {{pcori_dsp_interventi.first_name}}.  However, a team of experts carefully chose the information and resources that were used to generate the suggestions for this checklist. Whenever possible, these references are based on national healthcare guidelines for people with Down syndrome and are reviewed periodically for consistency with current evidence. The content of this checklist is not meant to be complete or absolute or to be a substitute for professional medical advice, diagnosis, or treatment. It should not be used to make a diagnosis or to replace or overrule a qualified health care provider's judgment.</p>\n" +
				"\n" +
				"    <p>Neither you nor your primary care provider will have the opportunity to discuss clinical or personal information with physicians at MGH. If needed, there are team members who are happy to answer any technical questions that may arise in the course of the study.</p>\n" +
				"    <br/>\n" +
				"     \n" +
				"    <p><img src=\"https://sprouttest.partners.org/sproutassets/brands/pcori-dsp/images/checkmark.png\" class=\"img-checkmark\"></img> You might choose to check each item when completed for {{pcori_dsp_interventi.first_name}}.</p>\n" +
				"\n" +
				"    <!-- End caregiver intro -->\n" +
				"\n" +
				"\n" +
				"    <!-- CAREGIVER URGENT -->\n" +
				"    <!-- CAREGIVER URGENT -->\n" +
				"    <!-- CAREGIVER URGENT\n" +
				"\n" +
				"    <hr></hr> \n" +
				"    <h2><font color=\"red\">Urgent Suggestions for {{pcori_dsp_interventi.first_name}}</font></h2>\n" +
				"    <h3><font color=\"red\">It is recommended that you do NOT wait for the next scheduled routine physical exam.  It is recommended that you contact Kristin’s doctor urgently to discuss the following suggestions:</font></h3>\n" +
				"\n" +
				"    insert cervical xray\n" +
				"\n" +
				"    End caregiver urgent -->\n" +
				"\n" +
				"\n" +
				"    <!-- CAREGIVER LABS TESTS PROCEDURES -->\n" +
				"    <!-- CAREGIVER LABS TESTS PROCEDURES -->\n" +
				"    <!-- CAREGIVER LABS TESTS PROCEDURES -->\n" +
				"\n" +
				"    <hr></hr>\n" +
				"    <h2>Labs, Tests, and Procedures for {{pcori_dsp_interventi.first_name}}</h2>\n" +
				"    <h3>It is recommended that you ask {{pcori_dsp_interventi.first_name}}'s doctor to consider ordering the following during the upcoming routine physical exam:</h3>\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.audio_12m.value \"==\" \"0\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td>\n" +
				"                    <p><strong>Audiogram/hearing test.</strong> {{pcori_dsp_interventi.first_name}} is due for a hearing test. <a href=\"http://www.massgeneral.org/children/print/downsyndrome-hearing-loss-children-down-syndrome.pdf\" target=\"_blank\">More information on hearing loss and Down syndrome. {{#i18n \"linkout\"}}{{/i18n}}</a> <a href=\"https://meeting.childrens.harvard.edu/p433sdirm5d/?proto=true\" target=\"_blank\">Evaluation and treatment options [video]. {{#i18n \"linkout\"}}{{/i18n}}</a> <span class=\"why\">{{#i18n \"why\"}}{{/i18n}}</span></p>\n" +
				"                    <div class=\"well\">Up to 75 of every 100 Individuals with Down syndrome have hearing loss; the American Academy of Pediatrics recommends all individuals between the ages of 1 and 21 have an annual audiogram. You indicated that {{pcori_dsp_interventi.first_name}} did not have an audiogram within the past year.</div>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.audio_2y.value \"==\" \"0\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td>\n" +
				"                    <p><strong>Audiogram/hearing test.</strong> {{pcori_dsp_interventi.first_name}} is due for a hearing test. <a href=\"http://www.ndss.org/Resources/Health-Care/Associated-Conditions/Ear-Nose-Throat-Issues-Down-Syndrome/\" target=\"_blank\">More information on hearing loss and Down syndrome. {{#i18n \"linkout\"}}{{/i18n}}</a> <a href=\"https://meeting.childrens.harvard.edu/p433sdirm5d/?proto=true\" target=\"_blank\">Evaluation and treatment options [video]. {{#i18n \"linkout\"}}{{/i18n}}</a> <span class=\"why\">{{#i18n \"why\"}}{{/i18n}}</span></p>\n" +
				"                    <div class=\"well\">Up to 75 of every 100 individuals with Down syndrome have hearing loss; experts specializing in Down syndrome recommend all individuals over the age of 21 have an audiogram every two years. You indicated that {{pcori_dsp_interventi.first_name}} did not have an audiogram within the past two years.</div>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.audio_12m.value \"==\" \"999\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td>\n" +
				"                    <p><strong>Audiogram/hearing test.</strong> {{pcori_dsp_interventi.first_name}} might be due for a hearing test. <a href=\"http://www.massgeneral.org/children/print/downsyndrome-hearing-loss-children-down-syndrome.pdf\" target=\"_blank\">More information on hearing loss and Down syndrome. {{#i18n \"linkout\"}}{{/i18n}}</a> <a href=\"https://meeting.childrens.harvard.edu/p433sdirm5d/?proto=true\" target=\"_blank\">Evaluation and treatment options [video]. {{#i18n \"linkout\"}}{{/i18n}}</a> <span class=\"why\">{{#i18n \"why\"}}{{/i18n}}</span></p>\n" +
				"                    <div class=\"well\">Up to 75 of every 100 Individuals with Down syndrome have hearing loss; the American Academy of Pediatrics recommends all individuals between the ages of 1 and 21 have an annual audiogram. You indicated that you are unsure if {{pcori_dsp_interventi.first_name}} had an audiogram within the past year.</div>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.audio_2y.value \"==\" \"999\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td>\n" +
				"                    <p><strong>Audiogram/hearing test.</strong> {{pcori_dsp_interventi.first_name}} might be due for a hearing test. <a href=\"http://www.ndss.org/Resources/Health-Care/Associated-Conditions/Ear-Nose-Throat-Issues-Down-Syndrome/\" target=\"_blank\">More information on hearing loss and Down syndrome. {{#i18n \"linkout\"}}{{/i18n}}</a> <a href=\"https://meeting.childrens.harvard.edu/p433sdirm5d/?proto=true\" target=\"_blank\">Evaluation and treatment options [video]. {{#i18n \"linkout\"}}{{/i18n}}</a> <span class=\"why\">{{#i18n \"why\"}}{{/i18n}}</span></p>\n" +
				"                    <div class=\"well\">Up to 75 of every 100 individuals with Down syndrome have hearing loss; experts specializing in Down syndrome recommend all individuals over the age of 21 have an audiogram every two years. You indicated that you are unsure if {{pcori_dsp_interventi.first_name}} had an audiogram within the two years.</div>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.ophtho_1y.value \"==\" \"0\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td>\n" +
				"                    <p><strong>Eye exam.</strong> {{pcori_dsp_interventi.first_name}} is due for a vision test by an eye doctor specialist. <a href=\"http://www.ndss.org/Resources/Health-Care/Associated-Conditions/Vision--Down-Syndrome/\" target=\"_blank\">More information on vision changes and Down syndrome. {{#i18n \"linkout\"}}{{/i18n}}</a> <a href=\"https://meeting.childrens.harvard.edu/p5b256vlmqj/?proto=true\" target=\"_blank\">Evaluation and treatment options [video]. {{#i18n \"linkout\"}}{{/i18n}}</a> <span class=\"why\">{{#i18n \"why\"}}{{/i18n}}</span></p>\n" +
				"                    <div class=\"well\">Up to 80 of every 100 children with Down syndrome have eye problems; the American Academy of Pediatrics recommends all individuals between the ages of 1 and 5 to have an annual eye exam. You indicated that {{pcori_dsp_interventi.first_name}} did not have an eye exam by an eye doctor specialist within the past year.</div>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.ophtho_2y.value \"==\" \"0\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td>\n" +
				"                    <p><strong>Eye exam.</strong> {{pcori_dsp_interventi.first_name}} is due for a vision test by an eye doctor specialist. <a href=\"http://www.ndss.org/Resources/Health-Care/Associated-Conditions/Vision--Down-Syndrome/\" target=\"_blank\">More information on vision changes and Down syndrome. {{#i18n \"linkout\"}}{{/i18n}}</a> <a href=\"https://meeting.childrens.harvard.edu/p5b256vlmqj/?proto=true\" target=\"_blank\">Evaluation and treatment options [video]. {{#i18n \"linkout\"}}{{/i18n}}</a> <span class=\"why\">{{#i18n \"why\"}}{{/i18n}}</span></p>\n" +
				"                    <div class=\"well\">Up to 80 of every 100 children with Down syndrome have eye problems; the American Academy of Pediatrics recommends all individuals between the ages of 5 and 13 to have an eye exam every two years or more frequently, if needed. You indicated that {{pcori_dsp_interventi.first_name}} did not have an eye exam within the past two years.</div>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.ophtho_3y.value \"==\" \"0\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td>\n" +
				"                    <p><strong>Eye exam.</strong> {{pcori_dsp_interventi.first_name}} is due for a vision test by an eye doctor specialist. <a href=\"http://www.ndss.org/Resources/Health-Care/Associated-Conditions/Vision--Down-Syndrome/\" target=\"_blank\">More information on vision changes and Down syndrome. {{#i18n \"linkout\"}}{{/i18n}}</a> <a href=\"https://meeting.childrens.harvard.edu/p5b256vlmqj/?proto=true\" target=\"_blank\">Evaluation and treatment options [video]. {{#i18n \"linkout\"}}{{/i18n}}</a> <span class=\"why\">{{#i18n \"why\"}}{{/i18n}}</span></p>\n" +
				"                    <div class=\"well\">Up to 80 of every 100 children with Down syndrome have eye problems; the American Academy of Pediatrics recommends all individuals between the ages of 13 and 21 to have an eye exam every three years or more frequently, if needed. You indicated that {{pcori_dsp_interventi.first_name}} did not have an eye exam within the past three years.</div>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.ophtho_2yadult.value \"==\" \"0\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td>\n" +
				"                    <p><strong>Eye exam.</strong> {{pcori_dsp_interventi.first_name}} is due for a vision test by an eye doctor specialist. <a href=\"http://www.ndss.org/Resources/Health-Care/Associated-Conditions/Vision--Down-Syndrome/\" target=\"_blank\">More information on vision changes and Down syndrome. {{#i18n \"linkout\"}}{{/i18n}}</a> <a href=\"https://meeting.childrens.harvard.edu/p5b256vlmqj/?proto=true\" target=\"_blank\">Evaluation and treatment options [video]. {{#i18n \"linkout\"}}{{/i18n}}</a> <span class=\"why\">{{#i18n \"why\"}}{{/i18n}}</span></p>\n" +
				"                    <div class=\"well\">The majority of adults with Down syndrome have eye problems; experts specializing in Down syndrome recommend all individuals over the age of 21 to have an eye exam every two years or more frequently if needed. You indicated that {{pcori_dsp_interventi.first_name}} did not have an eye exam within the past two years.</div>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.ophtho_1y.value \"==\" \"999\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td>\n" +
				"                    <p><strong>Eye exam.</strong> {{pcori_dsp_interventi.first_name}} may be due for a vision test by an eye doctor specialist. <a href=\"http://www.ndss.org/Resources/Health-Care/Associated-Conditions/Vision--Down-Syndrome/\" target=\"_blank\">More information on vision changes and Down syndrome. {{#i18n \"linkout\"}}{{/i18n}}</a> <a href=\"https://meeting.childrens.harvard.edu/p5b256vlmqj/?proto=true\" target=\"_blank\">Evaluation and treatment options [video]. {{#i18n \"linkout\"}}{{/i18n}}</a> <span class=\"why\">{{#i18n \"why\"}}{{/i18n}}</span></p>\n" +
				"                    <div class=\"well\">Up to 80 of every 100 children with Down syndrome have eye problems; the American Academy of Pediatrics recommends all individuals between the ages of 1 and 5 to have an annual eye exam. You indicated that you are unsure whether or not {{pcori_dsp_interventi.first_name}} has had an eye exam by an eye doctor specialist within the past year.</div>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.ophtho_2y.value \"==\" \"999\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td>\n" +
				"                    <p><strong>Eye exam.</strong> {{pcori_dsp_interventi.first_name}} may be due for a vision test by an eye doctor specialist. <a href=\"http://www.ndss.org/Resources/Health-Care/Associated-Conditions/Vision--Down-Syndrome/\" target=\"_blank\">More information on vision changes and Down syndrome. {{#i18n \"linkout\"}}{{/i18n}}</a> <a href=\"https://meeting.childrens.harvard.edu/p5b256vlmqj/?proto=true\" target=\"_blank\">Evaluation and treatment options [video]. {{#i18n \"linkout\"}}{{/i18n}}</a> <span class=\"why\">{{#i18n \"why\"}}{{/i18n}}</span></p>\n" +
				"                    <div class=\"well\">Up to 80 of every 100 children with Down syndrome have eye problems; the American Academy of Pediatrics recommends all individuals between the ages of 5 and 13 to have an eye exam every two years or more frequently, if needed. You indicated that you are unsure whether or not {{pcori_dsp_interventi.first_name}} has had an eye exam by an eye doctor specialist within the past two years.</div>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.ophtho_3y.value \"==\" \"999\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td>\n" +
				"                    <p><strong>Eye exam.</strong> {{pcori_dsp_interventi.first_name}} may be due for a vision test by an eye doctor specialist. <a href=\"http://www.ndss.org/Resources/Health-Care/Associated-Conditions/Vision--Down-Syndrome/\" target=\"_blank\">More information on vision changes and Down syndrome. {{#i18n \"linkout\"}}{{/i18n}}</a> <a href=\"https://meeting.childrens.harvard.edu/p5b256vlmqj/?proto=true\" target=\"_blank\">Evaluation and treatment options [video]. {{#i18n \"linkout\"}}{{/i18n}}</a> <span class=\"why\">{{#i18n \"why\"}}{{/i18n}}</span></p>\n" +
				"                    <div class=\"well\">Up to 80 of every 100 children with Down syndrome have eye problems; the American Academy of Pediatrics recommends all individuals between the ages of 13 and 21 to have an eye exam every three years or more frequently, if needed. You indicated that you are unsure whether or not {{pcori_dsp_interventi.first_name}} has had an eye exam by an eye doctor specialist within the past three years.</div>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.ophtho_2yadult.value \"==\" \"999\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td>\n" +
				"                    <p><strong>Eye exam.</strong> {{pcori_dsp_interventi.first_name}} may be due for a vision test by an eye doctor specialist. <a href=\"http://www.ndss.org/Resources/Health-Care/Associated-Conditions/Vision--Down-Syndrome/\" target=\"_blank\">More information on vision changes and Down syndrome. {{#i18n \"linkout\"}}{{/i18n}}</a> <a href=\"https://meeting.childrens.harvard.edu/p5b256vlmqj/?proto=true\" target=\"_blank\">Evaluation and treatment options [video]. {{#i18n \"linkout\"}}{{/i18n}}</a> <span class=\"why\">{{#i18n \"why\"}}{{/i18n}}</span></p>\n" +
				"                    <div class=\"well\">The majority of adults with Down syndrome have eye problems; experts specializing in Down syndrome recommend all individuals over the age of 21 to have an eye exam every two years or more frequently if needed. You indicated that you are unsure whether or not {{pcori_dsp_interventi.first_name}} has had an eye exam by an eye doctor specialist within the past two years.</div>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.celiac_test.value \"==\" \"0\"}}\n" +
				"    {{#if (or\n" +
				"        (eq pcori_dsp_interventi.hidden_celiac1 \"1\")\n" +
				"        (eq pcori_dsp_interventi.hidden_celiac2 \"1\")\n" +
				"    )}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td>\n" +
				"                    <p><strong>Celiac screen</strong> (blood work). {{pcori_dsp_interventi.first_name}} has symptoms that might be consistent with <a href=\"https://meeting.childrens.harvard.edu/p5u78g3myoy/?proto=true\" target=\"_blank\">celiac disease [Video] {{#i18n \"linkout\"}}{{/i18n}}</a>. <a href=\"http://www.massgeneral.org/children/down-syndrome/celiac-disease.aspx\" target=\"_blank\">Further testing is needed {{#i18n \"linkout\"}}{{/i18n}}</a>, with <a href=\"http://www.massgeneral.org/children/celiac-disease/bloodtest-results.aspx\" target=\"_blank\">potential follow-up testing {{#i18n \"linkout\"}}{{/i18n}}</a>. Treatment options are available for those with a confirmed diagnosis (<a href=\"http://www.massgeneral.org/children/celiac-disease/gi-gluten-free-diet-tips.aspx\" target=\"_blank\">brief handout {{#i18n \"linkout\"}}{{/i18n}}</a> and <a href=\"http://www.gikids.org/files/documents/resources/Gluten-FreeDietGuideWeb.pdf\" target=\"_blank\">more information on gluten-free foods {{#i18n \"linkout\"}}{{/i18n}}</a>).  <span class=\"why\">{{#i18n \"why\"}}{{/i18n}}</span></p>\n" +
				"                    <div class=\"well\">You indicated that within the past month, {{pcori_dsp_interventi.first_name}} has experienced \n" +
				"                        {{#compare pcori_dsp_interventi.constipation.value \"==\" \"1\"}}constipation{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.diarrhea.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.nausea.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.vomiting.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.stool.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.incontinence_stool.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.flatulence.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bloating.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.ftt.value \"1\")\n" +
				"                            )}}, {{else}}{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.hyperactivity_adhd.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.inattention.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.impulsivity.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.distracted.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.uncooperative.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.temper_meltdowns_irritability.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.diarrhea.value \"==\" \"1\"}}diarrhea{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.nausea.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.vomiting.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.stool.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.incontinence_stool.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.flatulence.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bloating.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.ftt.value \"1\")\n" +
				"                            )}}, {{else}}{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.hyperactivity_adhd.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.inattention.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.impulsivity.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.distracted.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.uncooperative.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.temper_meltdowns_irritability.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.nausea.value \"==\" \"1\"}}nausea{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.vomiting.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.stool.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.incontinence_stool.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.flatulence.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bloating.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.ftt.value \"1\")\n" +
				"                            )}}, {{else}}{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.hyperactivity_adhd.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.inattention.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.impulsivity.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.distracted.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.uncooperative.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.temper_meltdowns_irritability.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.vomiting.value \"==\" \"1\"}}vomiting{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.stool.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.incontinence_stool.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.flatulence.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bloating.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.ftt.value \"1\")\n" +
				"                            )}}, {{else}}{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.hyperactivity_adhd.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.inattention.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.impulsivity.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.distracted.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.uncooperative.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.temper_meltdowns_irritability.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.stool.value \"==\" \"1\"}}bulky or foul-smelling stools{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.incontinence_stool.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.flatulence.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bloating.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.ftt.value \"1\")\n" +
				"                            )}}, {{else}}{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.hyperactivity_adhd.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.inattention.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.impulsivity.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.distracted.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.uncooperative.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.temper_meltdowns_irritability.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.incontinence_stool.value \"==\" \"1\"}}new accidents with stool{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.flatulence.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bloating.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.ftt.value \"1\")\n" +
				"                            )}}, {{else}}{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.hyperactivity_adhd.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.inattention.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.impulsivity.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.distracted.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.uncooperative.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.temper_meltdowns_irritability.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.flatulence.value \"==\" \"1\"}}passing excessive gas{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.bloating.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.ftt.value \"1\")\n" +
				"                            )}}, {{else}}{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.hyperactivity_adhd.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.inattention.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.impulsivity.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.distracted.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.uncooperative.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.temper_meltdowns_irritability.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.bloating.value \"==\" \"1\"}}bloating{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.ftt.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hyperactivity_adhd.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.inattention.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.impulsivity.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.distracted.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.uncooperative.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.temper_meltdowns_irritability.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.ftt.value \"==\" \"1\"}}unexplained growth failure{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.hyperactivity_adhd.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.inattention.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.impulsivity.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.distracted.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.uncooperative.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.temper_meltdowns_irritability.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.hyperactivity_adhd.value \"==\" \"1\"}}overactive behavior, restlessness, inability to sit still{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.inattention.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.impulsivity.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.distracted.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.uncooperative.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.temper_meltdowns_irritability.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.inattention.value \"==\" \"1\"}}inattention{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.impulsivity.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.distracted.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.uncooperative.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.temper_meltdowns_irritability.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.impulsivity.value \"==\" \"1\"}}impulsivity, acting without thinking{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.distracted.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.uncooperative.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.temper_meltdowns_irritability.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.distracted.value \"==\" \"1\"}}easily distracted{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.uncooperative.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.temper_meltdowns_irritability.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.uncooperative.value \"==\" \"1\"}}uncooperative or disobedient behavior{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.temper_meltdowns_irritability.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.temper_meltdowns_irritability.value \"==\" \"1\"}}temper tantrums or outbursts or meltdowns{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.hits_self.value \"==\" \"1\"}}hitting self{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.bites_self.value \"==\" \"1\"}}biting self{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.injures_self.value \"==\" \"1\"}}hurting self on purpose{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.bangs_head.value \"==\" \"1\"}}banging head{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.irritable.value \"==\" \"1\"}}irritability{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.hits_others.value \"==\" \"1\"}}hitting or kicking others{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.mood_swings.value \"==\" \"1\"}}mood swings{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.aggression.value \"==\" \"1\"}}aggression{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.property_destruction.value \"==\" \"1\"}}property destruction{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.cries_easily.value \"==\" \"1\"}}crying easily or for no reason{{#compare pcori_dsp_interventi.lose_skills.value \"==\" \"1\"}}, {{else}}.{{/compare}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.lose_skills.value \"==\" \"1\"}}loss of previously learned skills.{{/compare}} \n" +
				"                    These symptoms could be consistent with celiac disease.  {{pcori_dsp_interventi.first_name}} has never had celiac testing done. \n" +
				"                    </div>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/if}}\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.celiac_test.value \"==\" \"999\"}}\n" +
				"    {{#if (or\n" +
				"        (eq pcori_dsp_interventi.hidden_celiac1 \"1\")\n" +
				"        (eq pcori_dsp_interventi.hidden_celiac2 \"1\")\n" +
				"    )}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td>\n" +
				"                    <p><strong>Celiac screen</strong> (blood work). {{pcori_dsp_interventi.first_name}} has symptoms that might be consistent with <a href=\"https://meeting.childrens.harvard.edu/p5u78g3myoy/?proto=true\" target=\"_blank\">celiac disease [Video] {{#i18n \"linkout\"}}{{/i18n}}</a>. <a href=\"http://www.massgeneral.org/children/down-syndrome/celiac-disease.aspx\" target=\"_blank\">Further testing is needed {{#i18n \"linkout\"}}{{/i18n}}</a>, with <a href=\"http://www.massgeneral.org/children/celiac-disease/bloodtest-results.aspx\" target=\"_blank\">potential follow-up testing {{#i18n \"linkout\"}}{{/i18n}}</a>. Treatment options are available for those with a confirmed diagnosis (<a href=\"http://www.massgeneral.org/children/celiac-disease/gi-gluten-free-diet-tips.aspx\" target=\"_blank\">brief handout {{#i18n \"linkout\"}}{{/i18n}}</a> and <a href=\"http://www.gikids.org/files/documents/resources/Gluten-FreeDietGuideWeb.pdf\" target=\"_blank\">more information on gluten-free foods {{#i18n \"linkout\"}}{{/i18n}}</a>).  <span class=\"why\">{{#i18n \"why\"}}{{/i18n}}</span></p>\n" +
				"                    <div class=\"well\">You indicated that within the past month, {{pcori_dsp_interventi.first_name}} has experienced \n" +
				"                        {{#compare pcori_dsp_interventi.constipation.value \"==\" \"1\"}}constipation{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.diarrhea.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.nausea.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.vomiting.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.stool.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.incontinence_stool.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.flatulence.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bloating.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.ftt.value \"1\")\n" +
				"                            )}}, {{else}}{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.hyperactivity_adhd.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.inattention.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.impulsivity.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.distracted.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.uncooperative.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.temper_meltdowns_irritability.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.diarrhea.value \"==\" \"1\"}}diarrhea{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.nausea.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.vomiting.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.stool.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.incontinence_stool.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.flatulence.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bloating.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.ftt.value \"1\")\n" +
				"                            )}}, {{else}}{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.hyperactivity_adhd.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.inattention.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.impulsivity.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.distracted.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.uncooperative.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.temper_meltdowns_irritability.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.nausea.value \"==\" \"1\"}}nausea{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.vomiting.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.stool.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.incontinence_stool.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.flatulence.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bloating.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.ftt.value \"1\")\n" +
				"                            )}}, {{else}}{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.hyperactivity_adhd.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.inattention.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.impulsivity.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.distracted.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.uncooperative.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.temper_meltdowns_irritability.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.vomiting.value \"==\" \"1\"}}vomiting{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.stool.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.incontinence_stool.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.flatulence.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bloating.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.ftt.value \"1\")\n" +
				"                            )}}, {{else}}{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.hyperactivity_adhd.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.inattention.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.impulsivity.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.distracted.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.uncooperative.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.temper_meltdowns_irritability.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.stool.value \"==\" \"1\"}}bulky or foul-smelling stools{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.incontinence_stool.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.flatulence.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bloating.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.ftt.value \"1\")\n" +
				"                            )}}, {{else}}{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.hyperactivity_adhd.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.inattention.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.impulsivity.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.distracted.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.uncooperative.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.temper_meltdowns_irritability.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.incontinence_stool.value \"==\" \"1\"}}new accidents with stool{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.flatulence.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bloating.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.ftt.value \"1\")\n" +
				"                            )}}, {{else}}{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.hyperactivity_adhd.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.inattention.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.impulsivity.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.distracted.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.uncooperative.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.temper_meltdowns_irritability.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.flatulence.value \"==\" \"1\"}}passing excessive gas{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.bloating.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.ftt.value \"1\")\n" +
				"                            )}}, {{else}}{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.hyperactivity_adhd.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.inattention.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.impulsivity.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.distracted.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.uncooperative.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.temper_meltdowns_irritability.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.bloating.value \"==\" \"1\"}}bloating{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.ftt.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hyperactivity_adhd.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.inattention.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.impulsivity.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.distracted.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.uncooperative.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.temper_meltdowns_irritability.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.ftt.value \"==\" \"1\"}}unexplained growth failure{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.hyperactivity_adhd.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.inattention.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.impulsivity.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.distracted.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.uncooperative.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.temper_meltdowns_irritability.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.hyperactivity_adhd.value \"==\" \"1\"}}overactive behavior, restlessness, inability to sit still{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.inattention.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.impulsivity.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.distracted.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.uncooperative.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.temper_meltdowns_irritability.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.inattention.value \"==\" \"1\"}}inattention{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.impulsivity.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.distracted.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.uncooperative.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.temper_meltdowns_irritability.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.impulsivity.value \"==\" \"1\"}}impulsivity, acting without thinking{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.distracted.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.uncooperative.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.temper_meltdowns_irritability.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.distracted.value \"==\" \"1\"}}easily distracted{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.uncooperative.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.temper_meltdowns_irritability.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.uncooperative.value \"==\" \"1\"}}uncooperative or disobedient behavior{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.temper_meltdowns_irritability.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.temper_meltdowns_irritability.value \"==\" \"1\"}}temper tantrums or outbursts or meltdowns{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.hits_self.value \"==\" \"1\"}}hitting self{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.bites_self.value \"==\" \"1\"}}biting self{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.injures_self.value \"==\" \"1\"}}hurting self on purpose{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.bangs_head.value \"==\" \"1\"}}banging head{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.irritable.value \"==\" \"1\"}}irritability{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.hits_others.value \"==\" \"1\"}}hitting or kicking others{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.mood_swings.value \"==\" \"1\"}}mood swings{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.aggression.value \"==\" \"1\"}}aggression{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.property_destruction.value \"==\" \"1\"}}property destruction{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.cries_easily.value \"==\" \"1\"}}crying easily or for no reason{{#compare pcori_dsp_interventi.lose_skills.value \"==\" \"1\"}}, {{else}}.{{/compare}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.lose_skills.value \"==\" \"1\"}}loss of previously learned skills.{{/compare}} \n" +
				"                    These symptoms could be consistent with celiac disease. You are also not sure whether {{pcori_dsp_interventi.first_name}} has ever had celiac testing done. You should discuss this with {{pcori_dsp_interventi.first_name}}’s provider and consider testing if bloodwork has not been done within the past 12 months.\n" +
				"                    </div>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/if}}\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.celiac_test.value \"==\" \"1\"}}\n" +
				"    {{#compare pcori_dsp_interventi.celiac_time.value \"==\" \"0\"}}\n" +
				"    {{#compare pcori_dsp_interventi.celiacd.value \"!=\" \"1\"}}\n" +
				"    {{#if (or\n" +
				"        (eq pcori_dsp_interventi.hidden_celiac1 \"1\")\n" +
				"        (eq pcori_dsp_interventi.hidden_celiac2 \"1\")\n" +
				"    )}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td>\n" +
				"                    <p><strong>Celiac screen</strong> (blood work). {{pcori_dsp_interventi.first_name}} has symptoms that might be consistent with <a href=\"https://meeting.childrens.harvard.edu/p5u78g3myoy/?proto=true\" target=\"_blank\">celiac disease [Video] {{#i18n \"linkout\"}}{{/i18n}}</a>. <a href=\"http://www.massgeneral.org/children/down-syndrome/celiac-disease.aspx\" target=\"_blank\">Further testing is needed {{#i18n \"linkout\"}}{{/i18n}}</a>, with <a href=\"http://www.massgeneral.org/children/celiac-disease/bloodtest-results.aspx\" target=\"_blank\">potential follow-up testing {{#i18n \"linkout\"}}{{/i18n}}</a>. Treatment options are available for those with a confirmed diagnosis (<a href=\"http://www.massgeneral.org/children/celiac-disease/gi-gluten-free-diet-tips.aspx\" target=\"_blank\">brief handout {{#i18n \"linkout\"}}{{/i18n}}</a> and <a href=\"http://www.gikids.org/files/documents/resources/Gluten-FreeDietGuideWeb.pdf\" target=\"_blank\">more information on gluten-free foods {{#i18n \"linkout\"}}{{/i18n}}</a>).  <span class=\"why\">{{#i18n \"why\"}}{{/i18n}}</span></p>\n" +
				"                    <div class=\"well\">You indicated that within the past month, {{pcori_dsp_interventi.first_name}} has experienced \n" +
				"                        {{#compare pcori_dsp_interventi.constipation.value \"==\" \"1\"}}constipation{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.diarrhea.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.nausea.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.vomiting.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.stool.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.incontinence_stool.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.flatulence.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bloating.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.ftt.value \"1\")\n" +
				"                            )}}, {{else}}{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.hyperactivity_adhd.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.inattention.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.impulsivity.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.distracted.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.uncooperative.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.temper_meltdowns_irritability.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.diarrhea.value \"==\" \"1\"}}diarrhea{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.nausea.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.vomiting.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.stool.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.incontinence_stool.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.flatulence.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bloating.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.ftt.value \"1\")\n" +
				"                            )}}, {{else}}{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.hyperactivity_adhd.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.inattention.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.impulsivity.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.distracted.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.uncooperative.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.temper_meltdowns_irritability.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.nausea.value \"==\" \"1\"}}nausea{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.vomiting.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.stool.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.incontinence_stool.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.flatulence.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bloating.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.ftt.value \"1\")\n" +
				"                            )}}, {{else}}{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.hyperactivity_adhd.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.inattention.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.impulsivity.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.distracted.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.uncooperative.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.temper_meltdowns_irritability.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.vomiting.value \"==\" \"1\"}}vomiting{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.stool.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.incontinence_stool.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.flatulence.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bloating.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.ftt.value \"1\")\n" +
				"                            )}}, {{else}}{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.hyperactivity_adhd.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.inattention.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.impulsivity.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.distracted.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.uncooperative.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.temper_meltdowns_irritability.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.stool.value \"==\" \"1\"}}bulky or foul-smelling stools{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.incontinence_stool.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.flatulence.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bloating.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.ftt.value \"1\")\n" +
				"                            )}}, {{else}}{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.hyperactivity_adhd.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.inattention.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.impulsivity.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.distracted.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.uncooperative.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.temper_meltdowns_irritability.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.incontinence_stool.value \"==\" \"1\"}}new accidents with stool{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.flatulence.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bloating.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.ftt.value \"1\")\n" +
				"                            )}}, {{else}}{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.hyperactivity_adhd.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.inattention.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.impulsivity.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.distracted.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.uncooperative.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.temper_meltdowns_irritability.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.flatulence.value \"==\" \"1\"}}passing excessive gas{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.bloating.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.ftt.value \"1\")\n" +
				"                            )}}, {{else}}{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.hyperactivity_adhd.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.inattention.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.impulsivity.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.distracted.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.uncooperative.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.temper_meltdowns_irritability.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.bloating.value \"==\" \"1\"}}bloating{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.ftt.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hyperactivity_adhd.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.inattention.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.impulsivity.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.distracted.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.uncooperative.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.temper_meltdowns_irritability.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.ftt.value \"==\" \"1\"}}unexplained growth failure{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.hyperactivity_adhd.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.inattention.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.impulsivity.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.distracted.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.uncooperative.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.temper_meltdowns_irritability.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.hyperactivity_adhd.value \"==\" \"1\"}}overactive behavior, restlessness, inability to sit still{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.inattention.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.impulsivity.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.distracted.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.uncooperative.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.temper_meltdowns_irritability.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.inattention.value \"==\" \"1\"}}inattention{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.impulsivity.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.distracted.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.uncooperative.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.temper_meltdowns_irritability.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.impulsivity.value \"==\" \"1\"}}impulsivity, acting without thinking{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.distracted.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.uncooperative.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.temper_meltdowns_irritability.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.distracted.value \"==\" \"1\"}}easily distracted{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.uncooperative.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.temper_meltdowns_irritability.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.uncooperative.value \"==\" \"1\"}}uncooperative or disobedient behavior{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.temper_meltdowns_irritability.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.temper_meltdowns_irritability.value \"==\" \"1\"}}temper tantrums or outbursts or meltdowns{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.hits_self.value \"==\" \"1\"}}hitting self{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.bites_self.value \"==\" \"1\"}}biting self{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.injures_self.value \"==\" \"1\"}}hurting self on purpose{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.bangs_head.value \"==\" \"1\"}}banging head{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.irritable.value \"==\" \"1\"}}irritability{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.hits_others.value \"==\" \"1\"}}hitting or kicking others{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.mood_swings.value \"==\" \"1\"}}mood swings{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.aggression.value \"==\" \"1\"}}aggression{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.property_destruction.value \"==\" \"1\"}}property destruction{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.cries_easily.value \"==\" \"1\"}}crying easily or for no reason{{#compare pcori_dsp_interventi.lose_skills.value \"==\" \"1\"}}, {{else}}.{{/compare}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.lose_skills.value \"==\" \"1\"}}loss of previously learned skills.{{/compare}} \n" +
				"                    These symptoms could be consistent with celiac disease, which can emerge at any point during the lifetime of someone with Down syndrome. You indicated that {{pcori_dsp_interventi.first_name}}  has not had celiac testing done within the past 12 months. \n" +
				"                    </div>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/if}}\n" +
				"    {{/compare}}\n" +
				"    {{/compare}}\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.celiac_test.value \"==\" \"1\"}}\n" +
				"    {{#compare pcori_dsp_interventi.celiac_time.value \"==\" \"0\"}}\n" +
				"    {{#compare pcori_dsp_interventi.celiacd.value \"==\" \"1\"}}\n" +
				"    {{#compare pcori_dsp_interventi.gluten_free.value \"==\" \"1\"}}\n" +
				"    {{#if (or\n" +
				"        (eq pcori_dsp_interventi.hidden_celiac1 \"1\")\n" +
				"        (eq pcori_dsp_interventi.hidden_celiac2 \"1\")\n" +
				"    )}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td>\n" +
				"                    <p><strong>Celiac screen</strong> because {{pcori_dsp_interventi.first_name}} might still have some unknown sources of gluten remaining in {{pcori_dsp_interventi.hishertheir}} diet. You might also find this <a href=\"https://meeting.childrens.harvard.edu/p5u78g3myoy/?proto=true\" target=\"_blank\">video {{#i18n \"linkout\"}}{{/i18n}}</a>, <a href=\"http://www.massgeneral.org/children/celiac-disease/gi-gluten-free-diet-tips.aspx\" target=\"_blank\">brief handout {{#i18n \"linkout\"}}{{/i18n}}</a>, and <a href=\"http://www.gikids.org/files/documents/resources/Gluten-FreeDietGuideWeb.pdf\" target=\"_blank\">more information on gluten-free foods {{#i18n \"linkout\"}}{{/i18n}}</a> to be helpful. Also, you can find a wealth of resources from <a href=\"http://www.bidmc.org/Centers-and-Departments/Departments/Digestive-Disease-Center/Services/Celiac-Center/CeliacNow.aspx\" target=\"_blank\">CeliacNow {{#i18n \"linkout\"}}{{/i18n}}</a>. <span class=\"why\">{{#i18n \"why\"}}{{/i18n}}</span></p>\n" +
				"                    <div class=\"well\">You indicated that within the past month, {{pcori_dsp_interventi.first_name}} has experienced \n" +
				"                        {{#compare pcori_dsp_interventi.constipation.value \"==\" \"1\"}}constipation{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.diarrhea.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.nausea.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.vomiting.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.stool.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.incontinence_stool.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.flatulence.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bloating.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.ftt.value \"1\")\n" +
				"                            )}}, {{else}}{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.hyperactivity_adhd.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.inattention.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.impulsivity.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.distracted.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.uncooperative.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.temper_meltdowns_irritability.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.diarrhea.value \"==\" \"1\"}}diarrhea{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.nausea.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.vomiting.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.stool.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.incontinence_stool.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.flatulence.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bloating.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.ftt.value \"1\")\n" +
				"                            )}}, {{else}}{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.hyperactivity_adhd.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.inattention.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.impulsivity.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.distracted.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.uncooperative.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.temper_meltdowns_irritability.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.nausea.value \"==\" \"1\"}}nausea{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.vomiting.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.stool.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.incontinence_stool.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.flatulence.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bloating.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.ftt.value \"1\")\n" +
				"                            )}}, {{else}}{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.hyperactivity_adhd.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.inattention.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.impulsivity.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.distracted.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.uncooperative.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.temper_meltdowns_irritability.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.vomiting.value \"==\" \"1\"}}vomiting{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.stool.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.incontinence_stool.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.flatulence.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bloating.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.ftt.value \"1\")\n" +
				"                            )}}, {{else}}{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.hyperactivity_adhd.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.inattention.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.impulsivity.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.distracted.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.uncooperative.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.temper_meltdowns_irritability.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.stool.value \"==\" \"1\"}}bulky or foul-smelling stools{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.incontinence_stool.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.flatulence.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bloating.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.ftt.value \"1\")\n" +
				"                            )}}, {{else}}{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.hyperactivity_adhd.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.inattention.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.impulsivity.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.distracted.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.uncooperative.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.temper_meltdowns_irritability.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.incontinence_stool.value \"==\" \"1\"}}new accidents with stool{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.flatulence.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bloating.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.ftt.value \"1\")\n" +
				"                            )}}, {{else}}{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.hyperactivity_adhd.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.inattention.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.impulsivity.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.distracted.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.uncooperative.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.temper_meltdowns_irritability.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.flatulence.value \"==\" \"1\"}}passing excessive gas{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.bloating.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.ftt.value \"1\")\n" +
				"                            )}}, {{else}}{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.hyperactivity_adhd.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.inattention.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.impulsivity.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.distracted.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.uncooperative.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.temper_meltdowns_irritability.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.bloating.value \"==\" \"1\"}}bloating{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.ftt.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hyperactivity_adhd.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.inattention.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.impulsivity.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.distracted.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.uncooperative.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.temper_meltdowns_irritability.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.ftt.value \"==\" \"1\"}}unexplained growth failure{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.hyperactivity_adhd.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.inattention.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.impulsivity.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.distracted.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.uncooperative.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.temper_meltdowns_irritability.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.hyperactivity_adhd.value \"==\" \"1\"}}overactive behavior, restlessness, inability to sit still{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.inattention.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.impulsivity.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.distracted.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.uncooperative.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.temper_meltdowns_irritability.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.inattention.value \"==\" \"1\"}}inattention{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.impulsivity.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.distracted.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.uncooperative.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.temper_meltdowns_irritability.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.impulsivity.value \"==\" \"1\"}}impulsivity, acting without thinking{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.distracted.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.uncooperative.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.temper_meltdowns_irritability.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.distracted.value \"==\" \"1\"}}easily distracted{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.uncooperative.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.temper_meltdowns_irritability.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.uncooperative.value \"==\" \"1\"}}uncooperative or disobedient behavior{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.temper_meltdowns_irritability.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.temper_meltdowns_irritability.value \"==\" \"1\"}}temper tantrums or outbursts or meltdowns{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.hits_self.value \"==\" \"1\"}}hitting self{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.bites_self.value \"==\" \"1\"}}biting self{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.injures_self.value \"==\" \"1\"}}hurting self on purpose{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.bangs_head.value \"==\" \"1\"}}banging head{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.irritable.value \"==\" \"1\"}}irritability{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.hits_others.value \"==\" \"1\"}}hitting or kicking others{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.mood_swings.value \"==\" \"1\"}}mood swings{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.aggression.value \"==\" \"1\"}}aggression{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.property_destruction.value \"==\" \"1\"}}property destruction{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.cries_easily.value \"==\" \"1\"}}crying easily or for no reason{{#compare pcori_dsp_interventi.lose_skills.value \"==\" \"1\"}}, {{else}}.{{/compare}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.lose_skills.value \"==\" \"1\"}}loss of previously learned skills.{{/compare}} \n" +
				"                    You also indicated that {{pcori_dsp_interventi.first_name}} has a diagnosis of celiac disease and is on a gluten-free diet.  Since {{pcori_dsp_interventi.first_name}} has not had celiac testing done within the past 12 months, you should talk to {{pcori_dsp_interventi.first_name}}’s doctor about using the celiac screen to see if any gluten might still be remaining in {{pcori_dsp_interventi.hishertheir}} diet.\n" +
				"                    </div>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/if}}\n" +
				"    {{/compare}}\n" +
				"    {{/compare}}\n" +
				"    {{/compare}}\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    <!-- insert sleep apnea labs -->\n" +
				"\n" +
				"    <!-- insert thyroid labs -->\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.pt_age \"<\" \"21\"}}\n" +
				"    {{#compare pcori_dsp_interventi.pmh_anemia.value \"!=\" \"1\"}}\n" +
				"    {{#compare pcori_dsp_interventi.hgb.value \"==\" \"0\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td>\n" +
				"                    <p><strong>Hemoglobin level</strong> (blood work). {{pcori_dsp_interventi.first_name}} is due for {{pcori_dsp_interventi.hishertheir}} check for anemia. <a href=\"http://www.massgeneral.org/children/print/down-syndrome-blood-tests-in-children.pdf\" target=\"_blank\">Further testing is needed. {{#i18n \"linkout\"}}{{/i18n}}</a> <a href=\"https://www.fns.usda.gov/sites/default/files/Nibbles_Newsletter_23.pdf\" target=\"_blank\">Treatment options may include increasing iron in the diet {{#i18n \"linkout\"}}{{/i18n}}</a> or starting an iron supplement.  Your provider might order a complete blood count (rather than just a hemoglobin level). <a href=\"http://www.ndss.org/Resources/Health-Care/Associated-Conditions/Blood-Diseases-Down-Syndrome/\" target=\"_blank\">If so, don’t be alarmed if some of the values are slightly abnormal. {{#i18n \"linkout\"}}{{/i18n}}</a>  <span class=\"why\">{{#i18n \"why\"}}{{/i18n}}</span></p>\n" +
				"                    <div class=\"well\">Children with Down syndrome are at risk of developing iron-deficiency anemia, which means that their hemoglobin, which is how oxygen is transported in our bodies, is low because the body isn’t receiving enough iron to make the hemoglobin.  Anemia can also affect cognitive function.  The American Academy of Pediatrics recommends testing the hemoglobin level every year, which will tell us whether or not anemia is present. If this is the case, your provider may consider adding iron to your diet, through diet or supplements, which helps resolve the anemia.</div>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"    {{/compare}}\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.pt_age \"<\" \"21\"}}\n" +
				"    {{#compare pcori_dsp_interventi.pmh_anemia.value \"==\" \"1\"}}\n" +
				"    {{#compare pcori_dsp_interventi.hgb.value \"==\" \"0\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td>\n" +
				"                    <p><strong>Hemoglobin level</strong> (blood work). You indicated that {{pcori_dsp_interventi.first_name}} already has a diagnosis of anemia but has not had  {{pcori_dsp_interventi.hishertheir}} blood work checked in the past year. <a href=\"http://www.massgeneral.org/children/print/down-syndrome-blood-tests-in-children.pdf\" target=\"_blank\">Further testing is needed {{#i18n \"linkout\"}}{{/i18n}}</a> to monitor the anemia and adjust treatment options. <a href=\"https://www.fns.usda.gov/sites/default/files/Nibbles_Newsletter_23.pdf\" target=\"_blank\">Here are some ways to increase iron in the diet. {{#i18n \"linkout\"}}{{/i18n}}</a> Your provider might also prescribe or adjust iron supplements. Your provider might order a complete blood count (rather than just a hemoglobin level). <a href=\"http://www.ndss.org/Resources/Health-Care/Associated-Conditions/Blood-Diseases-Down-Syndrome/\" target=\"_blank\">If so, don’t be alarmed if some of the values are slightly abnormal. {{#i18n \"linkout\"}}{{/i18n}}</a>  <span class=\"why\">{{#i18n \"why\"}}{{/i18n}}</span></p>\n" +
				"                    <div class=\"well\">Children with Down syndrome are at risk of developing iron-deficiency anemia, which means that their hemoglobin, which is how oxygen is transported in our bodies, is low because the body isn’t receiving enough iron to make the hemoglobin. Anemia can also affect cognitive function. The American Academy of Pediatrics recommends testing the hemoglobin level every year, which will tell us whether or not anemia is present and, if so, whether treatment or dietary options need to be adjusted. If this is the case, your provider may consider adding iron to your diet, through diet or supplements, which helps resolve the anemia.</div>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"    {{/compare}}\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.pt_age \"<\" \"21\"}}\n" +
				"    {{#compare pcori_dsp_interventi.pmh_anemia.value \"!=\" \"1\"}}\n" +
				"    {{#compare pcori_dsp_interventi.hgb.value \"==\" \"999\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td>\n" +
				"                    <p><strong>Hemoglobin level</strong> (blood work). You indicated that you are not sure whether {{pcori_dsp_interventi.first_name}} has had a hemoglobin tested in the past 12 months. <a href=\"http://www.massgeneral.org/children/print/down-syndrome-blood-tests-in-children.pdf\" target=\"_blank\">Further testing is needed. {{#i18n \"linkout\"}}{{/i18n}}</a> If {{pcori_dsp_interventi.first_name}} is found to have anemia, <a href=\"https://www.fns.usda.gov/sites/default/files/Nibbles_Newsletter_23.pdf\" target=\"_blank\">treatment options may include increasing iron in the diet {{#i18n \"linkout\"}}{{/i18n}}</a> or starting an iron supplement. Your provider might order a complete blood count (rather than just a hemoglobin level). <a href=\"http://www.ndss.org/Resources/Health-Care/Associated-Conditions/Blood-Diseases-Down-Syndrome/\" target=\"_blank\">If so, don’t be alarmed if some of the values are slightly abnormal. {{#i18n \"linkout\"}}{{/i18n}}</a>  <span class=\"why\">{{#i18n \"why\"}}{{/i18n}}</span></p>\n" +
				"                    <div class=\"well\">Children with Down syndrome are at risk of developing iron-deficiency anemia, which means that their hemoglobin, which is how oxygen is transported in our bodies, is low because the body isn’t receiving enough iron to make the hemoglobin.  Anemia can also affect cognitive function.  The American Academy of Pediatrics recommends testing the hemoglobin level every year, which will tell us whether or not anemia is present. If this is the case, your provider may consider adding iron to your diet, through diet or supplements, which helps resolve the anemia.</div>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"    {{/compare}}\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.pt_age \"<\" \"21\"}}\n" +
				"    {{#compare pcori_dsp_interventi.pmh_anemia.value \"==\" \"1\"}}\n" +
				"    {{#compare pcori_dsp_interventi.hgb.value \"==\" \"999\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td>\n" +
				"                    <p><strong>Hemoglobin level</strong> (blood work). You indicated that {{pcori_dsp_interventi.first_name}} already has a diagnosis of anemia but you are not sure if {{pcori_dsp_interventi.heshe}} had {{pcori_dsp_interventi.hishertheir}} blood work checked in the past year. <a href=\"http://www.massgeneral.org/children/print/down-syndrome-blood-tests-in-children.pdf\" target=\"_blank\">Further testing is needed {{#i18n \"linkout\"}}{{/i18n}}</a> to monitor the anemia and adjust treatment options. <a href=\"https://www.fns.usda.gov/sites/default/files/Nibbles_Newsletter_23.pdf\" target=\"_blank\">Here are some ways to increase iron in the diet. {{#i18n \"linkout\"}}{{/i18n}}</a> Your provider might also prescribe or adjust iron supplements. Your provider might order a complete blood count (rather than just a hemoglobin level). <a href=\"http://www.ndss.org/Resources/Health-Care/Associated-Conditions/Blood-Diseases-Down-Syndrome/\" target=\"_blank\">If so, don’t be alarmed if some of the values are slightly abnormal. {{#i18n \"linkout\"}}{{/i18n}}</a>  <span class=\"why\">{{#i18n \"why\"}}{{/i18n}}</span></p>\n" +
				"                    <div class=\"well\">Children with Down syndrome are at risk of developing iron-deficiency anemia, which means that their hemoglobin, which is how oxygen is transported in our bodies, is low because the body isn’t receiving enough iron to make the hemoglobin. Anemia can also affect cognitive function. The American Academy of Pediatrics recommends testing the hemoglobin level every year, which will tell us whether or not anemia is present and, if so, whether treatment or dietary options need to be adjusted. If this is the case, your provider may consider adding iron to your diet, through diet or supplements, which helps resolve the anemia. </div>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"    {{/compare}}\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    <!-- insert vfss labs -->\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.dentist.value \"==\" \"0\"}}\n" +
				"    {{#compare pcori_dsp_interventi.pmh_autism.value \"!=\" \"1\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td>\n" +
				"                    <p><strong>Dental visit.</strong> {{pcori_dsp_interventi.first_name}} is due for a dental visit.  <a href=\"http://www.ndss.org/Resources/Health-Care/Associated-Conditions/Dental-Issues-Down-Syndrome/\" target=\"_blank\">Regular dental care is important for all people with Down syndrome. {{#i18n \"linkout\"}}{{/i18n}}</a> Here is a <a href=\"https://meeting.childrens.harvard.edu/p47149871?proto=true\" target=\"_blank\">Webinar [Video] {{#i18n \"linkout\"}}{{/i18n}}</a> on common dental issues for people with Down syndrome.  <a href=\"http://www.massgeneral.org/children/print/down-syndrome-dentist-visit.pdf\" target=\"_blank\">Please see this handout for how to prepare for a dental visit. {{#i18n \"linkout\"}}{{/i18n}}</a>  <a href=\"https://www.amazon.com/Smiles-for-Grace/dp/0983623619\" target=\"_blank\">And, a book for {{pcori_dsp_interventi.first_name}} to read as they prepare for their visit. {{#i18n \"linkout\"}}{{/i18n}}</a>  And, here is a <a href=\"https://meeting.childrens.harvard.edu/p1hvnwkzc27/?proto=true\" target=\"_blank\">Webinar [Video] {{#i18n \"linkout\"}}{{/i18n}}</a> in case orthodontic care is needed. <span class=\"why\">{{#i18n \"why\"}}{{/i18n}}</span></p>\n" +
				"                    <div class=\"well\">You indicated on the intake that {{pcori_dsp_interventi.first_name}} has not seen a dentist in the past 6 months.</div>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.dentist.value \"==\" \"999\"}}\n" +
				"    {{#compare pcori_dsp_interventi.pmh_autism.value \"!=\" \"1\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td>\n" +
				"                    <p><strong>Dental visit.</strong> {{pcori_dsp_interventi.first_name}} might be due for a dental visit. <a href=\"http://www.ndss.org/Resources/Health-Care/Associated-Conditions/Dental-Issues-Down-Syndrome/\" target=\"_blank\">Regular dental care is important for all people with Down syndrome. {{#i18n \"linkout\"}}{{/i18n}}</a> Here is a <a href=\"https://meeting.childrens.harvard.edu/p47149871?proto=true\" target=\"_blank\">Webinar [Video] {{#i18n \"linkout\"}}{{/i18n}}</a> on common dental issues for people with Down syndrome.  <a href=\"http://www.massgeneral.org/children/print/down-syndrome-dentist-visit.pdf\" target=\"_blank\">Please see this handout for how to prepare for a dental visit. {{#i18n \"linkout\"}}{{/i18n}}</a>  <a href=\"https://www.amazon.com/Smiles-for-Grace/dp/0983623619\" target=\"_blank\">And, a book for {{pcori_dsp_interventi.first_name}} to read as they prepare for their visit. {{#i18n \"linkout\"}}{{/i18n}}</a>  And, here is a <a href=\"https://meeting.childrens.harvard.edu/p1hvnwkzc27/?proto=true\" target=\"_blank\">Webinar [Video] {{#i18n \"linkout\"}}{{/i18n}}</a> in case orthodontic care is needed. <span class=\"why\">{{#i18n \"why\"}}{{/i18n}}</span></p>\n" +
				"                    <div class=\"well\">You indicated on the intake that you are unsure if {{pcori_dsp_interventi.first_name}} has seen a dentist in the past 6 months.</div>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.dentist.value \"==\" \"0\"}}\n" +
				"    {{#compare pcori_dsp_interventi.pmh_autism.value \"==\" \"1\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td>\n" +
				"                    <p><strong>Dental visit.</strong> {{pcori_dsp_interventi.first_name}} is due for a dental visit.  <a href=\"http://www.ndss.org/Resources/Health-Care/Associated-Conditions/Dental-Issues-Down-Syndrome/\" target=\"_blank\">Regular dental care is important for all people with Down syndrome. {{#i18n \"linkout\"}}{{/i18n}}</a> <a href=\"https://www.autismspeaks.org/sites/default/files/documents/dentalguide.pdf\" target=\"_blank\">Here is a resource on dental health for people with autism. {{#i18n \"linkout\"}}{{/i18n}}  <a href=\"http://www.ndss.org/Global/oral_health_webinar.pdf\" target=\"_blank\">Here is another resource on dental care in people with Down syndrome. {{#i18n \"linkout\"}}{{/i18n}}</a> <a href=\"http://www.massgeneral.org/children/print/down-syndrome-dentist-visit.pdf\" target=\"_blank\">Please see this handout for how to prepare for a dental visit. {{#i18n \"linkout\"}}{{/i18n}}</a>  <a href=\"https://www.amazon.com/Smiles-for-Grace/dp/0983623619\" target=\"_blank\">And, a book for {{pcori_dsp_interventi.first_name}} to read as they prepare for their visit. {{#i18n \"linkout\"}}{{/i18n}}</a>  And, here is a <a href=\"https://meeting.childrens.harvard.edu/p1hvnwkzc27/?proto=true\" target=\"_blank\">Webinar [Video] {{#i18n \"linkout\"}}{{/i18n}}</a> in case orthodontic care is needed. <span class=\"why\">{{#i18n \"why\"}}{{/i18n}}</span></p>\n" +
				"                    <div class=\"well\">You indicated on the intake that {{pcori_dsp_interventi.first_name}} has not seen a dentist in the past 6 months, and also has a diagnosis of Autism-Spectrum Disorder.</div>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.dentist.value \"==\" \"999\"}}\n" +
				"    {{#compare pcori_dsp_interventi.pmh_autism.value \"==\" \"1\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td>\n" +
				"                    <p><strong>Dental visit.</strong> {{pcori_dsp_interventi.first_name}} might be due for a dental visit.  <a href=\"http://www.ndss.org/Resources/Health-Care/Associated-Conditions/Dental-Issues-Down-Syndrome/\" target=\"_blank\">Regular dental care is important for all people with Down syndrome. {{#i18n \"linkout\"}}{{/i18n}}</a> <a href=\"https://www.autismspeaks.org/sites/default/files/documents/dentalguide.pdf\" target=\"_blank\">Here is a resource on dental health for people with autism. {{#i18n \"linkout\"}}{{/i18n}}  <a href=\"http://www.ndss.org/Global/oral_health_webinar.pdf\" target=\"_blank\">Here is another resource on dental care in people with Down syndrome. {{#i18n \"linkout\"}}{{/i18n}}</a> <a href=\"http://www.massgeneral.org/children/print/down-syndrome-dentist-visit.pdf\" target=\"_blank\">Please see this handout for how to prepare for a dental visit. {{#i18n \"linkout\"}}{{/i18n}}</a>  <a href=\"https://www.amazon.com/Smiles-for-Grace/dp/0983623619\" target=\"_blank\">And, a book for {{pcori_dsp_interventi.first_name}} to read as they prepare for their visit. {{#i18n \"linkout\"}}{{/i18n}}</a>  And, here is a <a href=\"https://meeting.childrens.harvard.edu/p1hvnwkzc27/?proto=true\" target=\"_blank\">Webinar [Video] {{#i18n \"linkout\"}}{{/i18n}}</a> in case orthodontic care is needed. <span class=\"why\">{{#i18n \"why\"}}{{/i18n}}</span></p>\n" +
				"                    <div class=\"well\">You indicated on the intake that you are unsure if {{pcori_dsp_interventi.first_name}} has seen a dentist in the past 6 months,  and also has a diagnosis of Autism-Spectrum Disorder.</div>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.colonca_screening.value \"==\" \"0\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td>\n" +
				"                    <p><strong>Colon cancer screening.</strong> Testing might not necessarily be needed, but further discussion with {{pcori_dsp_interventi.first_name}}’s provider is suggested. <span class=\"why\">{{#i18n \"why\"}}{{/i18n}}</span></p>\n" +
				"                    <div class=\"well\">You indicated that {{pcori_dsp_interventi.first_name}} has never been screened for colon cancer. Research has demonstrated that colon cancer is less common in adults with Down syndrome.  We recommend discussing this with {{pcori_dsp_interventi.first_name}}’s provider, particularly if there is a family history of colon cancer or other risk factors.</div>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.colonca_screening.value \"==\" \"999\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td>\n" +
				"                    <p><strong>Colon cancer screening.</strong> Testing might not necessarily be needed, but further discussion with {{pcori_dsp_interventi.first_name}}’s provider is suggested. <span class=\"why\">{{#i18n \"why\"}}{{/i18n}}</span></p>\n" +
				"                    <div class=\"well\">You indicated that you were not sure if {{pcori_dsp_interventi.first_name}} has ever been screened for colon cancer. Research has demonstrated that colon cancer is less common in adults with Down syndrome.  We recommend discussing this with {{pcori_dsp_interventi.first_name}}’s provider, particularly if there is a family history of colon cancer or other risk factors.</div>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.mammogram.value \"==\" \"0\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td>\n" +
				"                    <p><strong>Mammogram.</strong> Testing might not necessarily be needed, but further discussion with {{pcori_dsp_interventi.first_name}}’s provider is suggested. <span class=\"why\">{{#i18n \"why\"}}{{/i18n}}</span></p>\n" +
				"                    <div class=\"well\">You indicated that {{pcori_dsp_interventi.first_name}} has never had a mammogram. Research has demonstrated that breast cancer is less common in adults with Down syndrome.  We recommend discussing this with {{pcori_dsp_interventi.first_name}}’s provider, particularly if there is a family history of breast cancer or other risk factors.</div>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.mammogram.value \"==\" \"999\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td>\n" +
				"                    <p><strong>Mammogram.</strong> Testing might not necessarily be needed, but further discussion with {{pcori_dsp_interventi.first_name}}’s provider is suggested. <span class=\"why\">{{#i18n \"why\"}}{{/i18n}}</span></p>\n" +
				"                    <div class=\"well\">You indicated that you were unsure if {{pcori_dsp_interventi.first_name}} has ever had a mammogram. Research has demonstrated that breast cancer is less common in adults with Down syndrome.  We recommend discussing this with {{pcori_dsp_interventi.first_name}}’s provider, particularly if there is a family history of breast cancer or other risk factors.</div>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.pap_smear.value \"==\" \"0\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td>\n" +
				"                    <p><strong>Pap smear.</strong> We recommend discussing this with {{pcori_dsp_interventi.first_name}}’s provider. <span class=\"why\">{{#i18n \"why\"}}{{/i18n}}</span></p>\n" +
				"                    <div class=\"well\">You indicated that {{pcori_dsp_interventi.first_name}} has never had a Pap smear. There is a very low risk of developing cervical cancer in the absence of having sexual intercourse. Nonetheless, experts specializing in Down syndrome recommend that adult women with Down syndrome consider having a Pap smear, particularly if they are sexually active. Performing a Pap smear on an adult woman who has not had sexual intercourse can be challenging and would be unlikely to provide benefit.</div>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.pap_smear.value \"==\" \"999\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td>\n" +
				"                    <p><strong>Pap Smear.</strong> We recommend discussing this with {{pcori_dsp_interventi.first_name}}’s provider. <span class=\"why\">{{#i18n \"why\"}}{{/i18n}}</span></p>\n" +
				"                    <div class=\"well\">You indicated that you were not sure if {{pcori_dsp_interventi.first_name}} has ever had a Pap smear. There is a very low risk of developing cervical cancer in the absence of having sexual intercourse. Nonetheless, experts specializing in Down syndrome recommend that adult women with Down syndrome consider having a Pap smear, particularly if they are sexually active. Performing a Pap smear on an adult woman who has not had sexual intercourse can be challenging and would be unlikely to provide benefit. </div>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.imm_influenza.value \"==\" \"2\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td>\n" +
				"                    <p><strong>Flu shot.</strong> <a href=\"!!!!!\" target=\"_blank\">More information on vaccines and Down syndrome. {{#i18n \"linkout\"}}{{/i18n}}</a>  <span class=\"why\">{{#i18n \"why\"}}{{/i18n}}</span></p>\n" +
				"                    <div class=\"well\">You indicated that {{pcori_dsp_interventi.first_name}} gets {{pcori_dsp_interventi.hishertheir}} flu vaccine “most years.” Experts recommend that people with Down syndrome get their flu shots every year, the same as everyone else in the general population. If this has not been done this year, we recommend discussing this with {{pcori_dsp_interventi.first_name}}’s provider.</div>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.imm_influenza.value \"==\" \"3\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td>\n" +
				"                    <p><strong>Flu shot.</strong> <a href=\"!!!!!\" target=\"_blank\">More information on vaccines and Down syndrome. {{#i18n \"linkout\"}}{{/i18n}}</a>  <span class=\"why\">{{#i18n \"why\"}}{{/i18n}}</span></p>\n" +
				"                    <div class=\"well\">You indicated that {{pcori_dsp_interventi.first_name}} gets {{pcori_dsp_interventi.hishertheir}} flu vaccine “some years.” Experts recommend that people with Down syndrome get their flu shots every year, the same as everyone else in the general population. If this has not been done this year, we recommend discussing this with {{pcori_dsp_interventi.first_name}}’s provider.</div>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.imm_influenza.value \"==\" \"4\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td>\n" +
				"                    <p><strong>Flu shot.</strong> <a href=\"!!!!!\" target=\"_blank\">More information on vaccines and Down syndrome. {{#i18n \"linkout\"}}{{/i18n}}</a>  <span class=\"why\">{{#i18n \"why\"}}{{/i18n}}</span></p>\n" +
				"                    <div class=\"well\">You indicated that {{pcori_dsp_interventi.first_name}} never gets {{pcori_dsp_interventi.hishertheir}} flu vaccine. Experts recommend that people with Down syndrome get their flu shots every year, the same as everyone else in the general population. We recommend discussing this with {{pcori_dsp_interventi.first_name}}’s provider.</div>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.imm_influenza.value \"==\" \"5\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td>\n" +
				"                    <p><strong>Flu shot.</strong> <a href=\"!!!!!\" target=\"_blank\">More information on vaccines and Down syndrome. {{#i18n \"linkout\"}}{{/i18n}}</a>  <span class=\"why\">{{#i18n \"why\"}}{{/i18n}}</span></p>\n" +
				"                    <div class=\"well\">You indicated that you are not sure how often {{pcori_dsp_interventi.first_name}} gets a flu vaccine. Experts recommend that people with Down syndrome get their flu shots every year, the same as everyone else in the general population. We recommend discussing this with {{pcori_dsp_interventi.first_name}}’s provider.</div>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.imm_pneumonia.value \"==\" \"0\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td>\n" +
				"                    <p><strong>Pneumonia vaccine.</strong> <a href=\"!!!!!\" target=\"_blank\">More information on vaccines and Down syndrome. {{#i18n \"linkout\"}}{{/i18n}}</a>  <span class=\"why\">{{#i18n \"why\"}}{{/i18n}}</span></p>\n" +
				"                    <div class=\"well\">Experts specializing in Down syndrome recommend that adults with Down syndrome receive pneumonia vaccines at an earlier age. Individuals with Down syndrome are at an increased risk of developing pneumonia due to a slightly weaker immune system. You indicated that {{pcori_dsp_interventi.first_name}} has not had a pneumonia vaccine in the past. We recommend discussing this with {{pcori_dsp_interventi.first_name}}’s provider.</div>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.imm_pneumonia.value \"==\" \"999\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td>\n" +
				"                    <p><strong>Pneumonia vaccine.</strong> <a href=\"!!!!!\" target=\"_blank\">More information on vaccines and Down syndrome. {{#i18n \"linkout\"}}{{/i18n}}</a>  <span class=\"why\">{{#i18n \"why\"}}{{/i18n}}</span></p>\n" +
				"                    <div class=\"well\">Experts specializing in Down syndrome recommend that adults with Down syndrome receive pneumonia vaccines at an earlier age. Individuals with Down syndrome are at an increased risk of developing pneumonia due to a slightly weaker immune system. You indicated that you are not sure whether or not {{pcori_dsp_interventi.first_name}} has had a pneumonia vaccine in the past. We recommend discussing this with {{pcori_dsp_interventi.first_name}}’s provider.</div>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.xxxxx.value \"==\" \"xxxxx\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td>\n" +
				"                    <p><strong>XXXXX.</strong> {{pcori_dsp_interventi.first_name}} XXXXXX <a href=\"xxxxx\" target=\"_blank\">More information on XXXXX. {{#i18n \"linkout\"}}{{/i18n}}</a>  <span class=\"why\">{{#i18n \"why\"}}{{/i18n}}</span></p>\n" +
				"                    <div class=\"well\">XXXXX</div>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"\n" +
				"\n" +
				"\n" +
				"    <!-- End caregiver labs tests procedures -->\n" +
				"\n" +
				"    <!-- CAREGIVER HEALTH WELLNESS RESOURCES -->\n" +
				"    <!-- CAREGIVER HEALTH WELLNESS RESOURCES -->\n" +
				"    <!-- CAREGIVER HEALTH WELLNESS RESOURCES -->\n" +
				"\n" +
				"    <hr></hr>\n" +
				"    <h2>Health and Wellness Resources for {{pcori_dsp_interventi.first_name}}</h2>\n" +
				"    <h3>Here are some specific resources based on your requests.</h3>\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.audio_12m.value \"==\" \"1\"}}\n" +
				"        {{#compare pcori_dsp_interventi.hearing_loss.value \"==\" \"1\"}}\n" +
				"            <table class=\"table-checklist\">\n" +
				"                <tr>\n" +
				"                    <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                    <td>\n" +
				"                        <p><strong>Hearing loss concerns.</strong> <a href=\"http://www.massgeneral.org/children/print/downsyndrome-hearing-loss-children-down-syndrome.pdf\" target=\"blank\">More information on hearing loss and Down syndrome. {{#i18n \"linkout\"}}{{/i18n}}</a> <a href=\"https://meeting.childrens.harvard.edu/p433sdirm5d/?proto=true\" target=\"_blank\">Evaluation and treatment options [video]. {{#i18n \"linkout\"}}{{/i18n}}</a> <span class=\"why\">{{#i18n \"why\"}}{{/i18n}}</span></p>\n" +
				"                        <div class=\"well\">Since {{pcori_dsp_interventi.first_name}} has had a hearing test within the last year, you might find this handout and webinar helpful because you indicated that you still had some concerns about hearing loss.  If you learn something new, you might consider discussing this handout and webinar with {{pcori_dsp_interventi.first_name}}'s provider.</div>\n" +
				"                </td>\n" +
				"                </tr>\n" +
				"            </table>\n" +
				"        {{/compare}}\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.audio_2y.value \"==\" \"1\"}}\n" +
				"        {{#compare pcori_dsp_interventi.hearing_loss.value \"==\" \"1\"}}\n" +
				"            <table class=\"table-checklist\">\n" +
				"                <tr>\n" +
				"                    <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                    <td>\n" +
				"                        <p><strong>Hearing loss concerns.</strong> <a href=\"http://www.ndss.org/Resources/Health-Care/Associated-Conditions/Ear-Nose-Throat-Issues-Down-Syndrome/\" target=\"_blank\">More information on hearing loss and Down syndrome. {{#i18n \"linkout\"}}{{/i18n}}</a> <a href=\"https://meeting.childrens.harvard.edu/p433sdirm5d/?proto=true\" target=\"_blank\">Evaluation and treatment options [video]. {{#i18n \"linkout\"}}{{/i18n}}</a> <span class=\"why\">{{#i18n \"why\"}}{{/i18n}}</span></p>\n" +
				"                        <div class=\"well\">Since {{pcori_dsp_interventi.first_name}} has had a hearing test within the last two years, you might find this handout and webinar helpful because you indicated that you still had some concerns about hearing loss.  If you learn something new, you might consider discussing this handout and webinar with {{pcori_dsp_interventi.first_name}}'s provider.</div>\n" +
				"                </td>\n" +
				"                </tr>\n" +
				"            </table>\n" +
				"        {{/compare}}\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#if (and\n" +
				"        (eq pcori_dsp_interventi.ophtho_1y.value \"1\")\n" +
				"        (eq pcori_dsp_interventi.concerns_about_vision.value \"1\")\n" +
				"        (eq pcori_dsp_interventi.keratoconus.value \"\")\n" +
				"        (eq pcori_dsp_interventi.cataracts.value \"\")\n" +
				"        (eq pcori_dsp_interventi.strabismus.value \"\")\n" +
				"        (eq pcori_dsp_interventi.nystagmus.value \"\")\n" +
				"        (eq pcori_dsp_interventi.astigmatism.value \"\")\n" +
				"    )}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td>\n" +
				"                    <p><strong>Vision concerns.</strong> <a href=\"http://www.ndss.org/Resources/Health-Care/Associated-Conditions/Vision--Down-Syndrome/\" target=\"_blank\">More information on vision changes and Down syndrome. {{#i18n \"linkout\"}}{{/i18n}}</a> <a href=\"https://meeting.childrens.harvard.edu/p5b256vlmqj/?proto=true\" target=\"_blank\">Evaluation and treatment options [Video]. {{#i18n \"linkout\"}}{{/i18n}}</a> <span class=\"why\">{{#i18n \"why\"}}{{/i18n}}</span></p>\n" +
				"                        <div class=\"well\">Since {{pcori_dsp_interventi.first_name}} has had an eye exam within the last year, you might find this handout helpful because you indicated that you still had some concerns about vision.  Consider asking your primary care doctor if a referral to an eye doctor would be helpful.</div>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/if}}\n" +
				"\n" +
				"    {{#if (and\n" +
				"        (eq pcori_dsp_interventi.ophtho_2y.value \"1\")\n" +
				"        (eq pcori_dsp_interventi.concerns_about_vision.value \"1\")\n" +
				"        (eq pcori_dsp_interventi.keratoconus.value \"\")\n" +
				"        (eq pcori_dsp_interventi.cataracts.value \"\")\n" +
				"        (eq pcori_dsp_interventi.strabismus.value \"\")\n" +
				"        (eq pcori_dsp_interventi.nystagmus.value \"\")\n" +
				"        (eq pcori_dsp_interventi.astigmatism.value \"\")\n" +
				"    )}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td>\n" +
				"                    <p><strong>Vision concerns.</strong> <a href=\"http://www.ndss.org/Resources/Health-Care/Associated-Conditions/Vision--Down-Syndrome/\" target=\"_blank\">More information on vision changes and Down syndrome. {{#i18n \"linkout\"}}{{/i18n}}</a> <a href=\"https://meeting.childrens.harvard.edu/p5b256vlmqj/?proto=true\" target=\"_blank\">Evaluation and treatment options [Video]. {{#i18n \"linkout\"}}{{/i18n}}</a> <span class=\"why\">{{#i18n \"why\"}}{{/i18n}}</span></p>\n" +
				"                        <div class=\"well\">Since {{pcori_dsp_interventi.first_name}} has had an eye exam within the last two years, you might find this handout helpful because you indicated that you still had some concerns about vision. Consider asking your primary care doctor if a referral to an eye doctor would be helpful.</div>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/if}}\n" +
				"\n" +
				"    {{#if (and\n" +
				"        (eq pcori_dsp_interventi.ophtho_3y.value \"1\")\n" +
				"        (eq pcori_dsp_interventi.concerns_about_vision.value \"1\")\n" +
				"        (eq pcori_dsp_interventi.keratoconus.value \"\")\n" +
				"        (eq pcori_dsp_interventi.cataracts.value \"\")\n" +
				"        (eq pcori_dsp_interventi.strabismus.value \"\")\n" +
				"        (eq pcori_dsp_interventi.nystagmus.value \"\")\n" +
				"        (eq pcori_dsp_interventi.astigmatism.value \"\")\n" +
				"    )}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td>\n" +
				"                    <p><strong>Vision concerns.</strong> <a href=\"http://www.ndss.org/Resources/Health-Care/Associated-Conditions/Vision--Down-Syndrome/\" target=\"_blank\">More information on vision changes and Down syndrome. {{#i18n \"linkout\"}}{{/i18n}}</a> <a href=\"https://meeting.childrens.harvard.edu/p5b256vlmqj/?proto=true\" target=\"_blank\">Evaluation and treatment options [Video]. {{#i18n \"linkout\"}}{{/i18n}}</a> <span class=\"why\">{{#i18n \"why\"}}{{/i18n}}</span></p>\n" +
				"                        <div class=\"well\">Since {{pcori_dsp_interventi.first_name}} has had an eye exam within the last three years, you might find this handout helpful because you indicated that you still had some concerns about vision. Consider asking your primary care doctor if a referral to an eye doctor would be helpful.</div>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/if}}\n" +
				"\n" +
				"    {{#if (and\n" +
				"        (eq pcori_dsp_interventi.ophtho_2yadult.value \"1\")\n" +
				"        (eq pcori_dsp_interventi.concerns_about_vision.value \"1\")\n" +
				"        (eq pcori_dsp_interventi.keratoconus.value \"\")\n" +
				"        (eq pcori_dsp_interventi.cataracts.value \"\")\n" +
				"        (eq pcori_dsp_interventi.strabismus.value \"\")\n" +
				"        (eq pcori_dsp_interventi.nystagmus.value \"\")\n" +
				"        (eq pcori_dsp_interventi.astigmatism.value \"\")\n" +
				"    )}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td>\n" +
				"                    <p><strong>Vision concerns.</strong> <a href=\"http://www.ndss.org/Resources/Health-Care/Associated-Conditions/Vision--Down-Syndrome/\" target=\"_blank\">More information on vision changes and Down syndrome. {{#i18n \"linkout\"}}{{/i18n}}</a> <a href=\"https://meeting.childrens.harvard.edu/p5b256vlmqj/?proto=true\" target=\"_blank\">Evaluation and treatment options [Video]. {{#i18n \"linkout\"}}{{/i18n}}</a> <span class=\"why\">{{#i18n \"why\"}}{{/i18n}}</span></p>\n" +
				"                        <div class=\"well\">Since {{pcori_dsp_interventi.first_name}} has had an eye exam within the last two years, you might find this handout helpful because you indicated that you still had some concerns about vision. Consider asking your primary care doctor if a referral to an eye doctor would be helpful.</div>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/if}}\n" +
				"\n" +
				"    {{#if (or\n" +
				"        (eq pcori_dsp_interventi.ophtho_1y.value \"1\")\n" +
				"        (eq pcori_dsp_interventi.ophtho_2y.value \"1\")\n" +
				"        (eq pcori_dsp_interventi.ophtho_3y.value \"1\")\n" +
				"        (eq pcori_dsp_interventi.ophtho_2yadult.value \"1\")\n" +
				"    )}}\n" +
				"    {{#if (and\n" +
				"        (eq pcori_dsp_interventi.concerns_about_vision.value \"1\")\n" +
				"        (eq pcori_dsp_interventi.keratoconus.value \"1\")\n" +
				"    )}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td>\n" +
				"                    <p><strong>Keratoconus.</strong> <a href=\"https://aapos.org/terms/conditions/64\" target=\"_blank\">More information on keratoconus and Down syndrome. {{#i18n \"linkout\"}}{{/i18n}}</a> <a href=\"http://www.nkcf.org/corneal-collagen-cross-linking/\" target=\"_blank\">A video on corneal cross-linking, a developing treatment option [Video]. {{#i18n \"linkout\"}}{{/i18n}}</a> <span class=\"why\">{{#i18n \"why\"}}{{/i18n}}</span></p>\n" +
				"                        <div class=\"well\">Since you indicated that you have some concerns about vision and {{pcori_dsp_interventi.first_name}} has a history of keratoconus, you might find this handout helpful.  If by reading this handout, you learn something new, you might consider printing it out and discussing with {{pcori_dsp_interventi.first_name}}’s provider.</div>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/if}}\n" +
				"    {{/if}}\n" +
				"\n" +
				"    {{#if (or\n" +
				"        (eq pcori_dsp_interventi.ophtho_1y.value \"1\")\n" +
				"        (eq pcori_dsp_interventi.ophtho_2y.value \"1\")\n" +
				"        (eq pcori_dsp_interventi.ophtho_3y.value \"1\")\n" +
				"        (eq pcori_dsp_interventi.ophtho_2yadult.value \"1\")\n" +
				"    )}}\n" +
				"    {{#if (and\n" +
				"        (eq pcori_dsp_interventi.concerns_about_vision.value \"1\")\n" +
				"        (eq pcori_dsp_interventi.cataracts.value \"1\")\n" +
				"    )}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td>\n" +
				"                    <p><strong>Cataracts.</strong> <a href=\"https://aapos.org/terms/conditions/31\" target=\"_blank\">More information on cataracts. {{#i18n \"linkout\"}}{{/i18n}}</a> <span class=\"why\">{{#i18n \"why\"}}{{/i18n}}</span></p>\n" +
				"                        <div class=\"well\">Since you indicated that you have some concerns about vision and {{pcori_dsp_interventi.first_name}} has a history of cataracts, you might find this handout helpful.  If by reading this handout, you learn something new, you might consider printing it out and discussing with {{pcori_dsp_interventi.first_name}}’s provider.</div>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/if}}\n" +
				"    {{/if}}\n" +
				"\n" +
				"    {{#if (or\n" +
				"        (eq pcori_dsp_interventi.ophtho_1y.value \"1\")\n" +
				"        (eq pcori_dsp_interventi.ophtho_2y.value \"1\")\n" +
				"        (eq pcori_dsp_interventi.ophtho_3y.value \"1\")\n" +
				"        (eq pcori_dsp_interventi.ophtho_2yadult.value \"1\")\n" +
				"    )}}\n" +
				"    {{#if (and\n" +
				"        (eq pcori_dsp_interventi.involuntary_eye_movements.value \"1\")\n" +
				"        (eq pcori_dsp_interventi.nystagmus.value \"1\")\n" +
				"    )}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td>\n" +
				"                    <p><strong>Nystagmus.</strong> <a href=\"https://aapos.org/terms/conditions/80\" target=\"_blank\">More information on nystagmus. {{#i18n \"linkout\"}}{{/i18n}}</a> <span class=\"why\">{{#i18n \"why\"}}{{/i18n}}</span></p>\n" +
				"                        <div class=\"well\">Since you indicated that you have some concerns about involuntary eye movements  and {{pcori_dsp_interventi.first_name}} has a history of nystagmus, you might find this handout helpful.  If by reading this handout, you learn something new, you might consider printing it out and discussing with {{pcori_dsp_interventi.first_name}}’s provider.</div>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/if}}\n" +
				"    {{/if}}\n" +
				"\n" +
				"        {{#if (or\n" +
				"        (eq pcori_dsp_interventi.ophtho_1y.value \"1\")\n" +
				"        (eq pcori_dsp_interventi.ophtho_2y.value \"1\")\n" +
				"        (eq pcori_dsp_interventi.ophtho_3y.value \"1\")\n" +
				"        (eq pcori_dsp_interventi.ophtho_2yadult.value \"1\")\n" +
				"    )}}\n" +
				"    {{#if (and\n" +
				"        (eq pcori_dsp_interventi.involuntary_eye_movements.value \"1\")\n" +
				"        (eq pcori_dsp_interventi.nystagmus.value \"\")\n" +
				"    )}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td>\n" +
				"                    <p><strong>Nystagmus.</strong> <a href=\"https://aapos.org/terms/conditions/80\" target=\"_blank\">More information on nystagmus. {{#i18n \"linkout\"}}{{/i18n}}</a> <span class=\"why\">{{#i18n \"why\"}}{{/i18n}}</span></p>\n" +
				"                        <div class=\"well\">You indicated that you have some concerns about {{pcori_dsp_interventi.first_name}} having involuntary eye movements.  This may be suggestive of a condition called “nystagmus.”  You should discuss this with {{pcori_dsp_interventi.first_name}}’s doctor, and you might find this handout to be helpful.</div>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/if}}\n" +
				"    {{/if}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.celiac_test.value \"==\" \"1\"}}\n" +
				"    {{#compare pcori_dsp_interventi.celiacd.value \"==\" \"1\"}}\n" +
				"    {{#compare pcori_dsp_interventi.gluten_free.value \"==\" \"2\"}}\n" +
				"    {{#if (or\n" +
				"        (eq pcori_dsp_interventi.hidden_celiac1 \"1\")\n" +
				"        (eq pcori_dsp_interventi.hidden_celiac2 \"1\")\n" +
				"    )}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td>\n" +
				"                    <p><strong>Gluten-free foods.</strong> <a href=\"http://www.massgeneral.org/children/celiac-disease/gi-gluten-free-diet-tips.aspx\" target=\"_blank\">Brief handout {{#i18n \"linkout\"}}{{/i18n}}</a> and <a href=\"http://www.gikids.org/files/documents/resources/Gluten-FreeDietGuideWeb.pdf\" target=\"_blank\">more information on gluten-free foods {{#i18n \"linkout\"}}{{/i18n}}</a>. Also, you can find a wealth of resources from <a href=\"http://www.bidmc.org/Centers-and-Departments/Departments/Digestive-Disease-Center/Services/Celiac-Center/CeliacNow.aspx\" target=\"_blank\">CeliacNow {{#i18n \"linkout\"}}{{/i18n}}</a>. <span class=\"why\">{{#i18n \"why\"}}{{/i18n}}</span></p>\n" +
				"                    <div class=\"well\">You indicated that within the past month, {{pcori_dsp_interventi.first_name}} has experienced \n" +
				"                        {{#compare pcori_dsp_interventi.constipation.value \"==\" \"1\"}}constipation{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.diarrhea.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.nausea.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.vomiting.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.stool.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.incontinence_stool.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.flatulence.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bloating.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.ftt.value \"1\")\n" +
				"                            )}}, {{else}}{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.hyperactivity_adhd.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.inattention.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.impulsivity.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.distracted.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.uncooperative.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.temper_meltdowns_irritability.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.diarrhea.value \"==\" \"1\"}}diarrhea{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.nausea.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.vomiting.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.stool.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.incontinence_stool.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.flatulence.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bloating.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.ftt.value \"1\")\n" +
				"                            )}}, {{else}}{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.hyperactivity_adhd.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.inattention.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.impulsivity.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.distracted.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.uncooperative.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.temper_meltdowns_irritability.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.nausea.value \"==\" \"1\"}}nausea{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.vomiting.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.stool.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.incontinence_stool.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.flatulence.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bloating.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.ftt.value \"1\")\n" +
				"                            )}}, {{else}}{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.hyperactivity_adhd.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.inattention.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.impulsivity.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.distracted.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.uncooperative.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.temper_meltdowns_irritability.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.vomiting.value \"==\" \"1\"}}vomiting{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.stool.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.incontinence_stool.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.flatulence.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bloating.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.ftt.value \"1\")\n" +
				"                            )}}, {{else}}{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.hyperactivity_adhd.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.inattention.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.impulsivity.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.distracted.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.uncooperative.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.temper_meltdowns_irritability.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.stool.value \"==\" \"1\"}}bulky or foul-smelling stools{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.incontinence_stool.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.flatulence.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bloating.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.ftt.value \"1\")\n" +
				"                            )}}, {{else}}{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.hyperactivity_adhd.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.inattention.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.impulsivity.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.distracted.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.uncooperative.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.temper_meltdowns_irritability.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.incontinence_stool.value \"==\" \"1\"}}new accidents with stool{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.flatulence.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bloating.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.ftt.value \"1\")\n" +
				"                            )}}, {{else}}{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.hyperactivity_adhd.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.inattention.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.impulsivity.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.distracted.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.uncooperative.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.temper_meltdowns_irritability.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.flatulence.value \"==\" \"1\"}}passing excessive gas{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.bloating.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.ftt.value \"1\")\n" +
				"                            )}}, {{else}}{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.hyperactivity_adhd.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.inattention.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.impulsivity.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.distracted.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.uncooperative.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.temper_meltdowns_irritability.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.bloating.value \"==\" \"1\"}}bloating{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.ftt.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hyperactivity_adhd.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.inattention.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.impulsivity.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.distracted.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.uncooperative.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.temper_meltdowns_irritability.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.ftt.value \"==\" \"1\"}}unexplained growth failure{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.hyperactivity_adhd.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.inattention.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.impulsivity.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.distracted.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.uncooperative.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.temper_meltdowns_irritability.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.hyperactivity_adhd.value \"==\" \"1\"}}overactive behavior, restlessness, inability to sit still{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.inattention.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.impulsivity.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.distracted.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.uncooperative.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.temper_meltdowns_irritability.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.inattention.value \"==\" \"1\"}}inattention{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.impulsivity.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.distracted.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.uncooperative.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.temper_meltdowns_irritability.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.impulsivity.value \"==\" \"1\"}}impulsivity, acting without thinking{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.distracted.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.uncooperative.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.temper_meltdowns_irritability.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.distracted.value \"==\" \"1\"}}easily distracted{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.uncooperative.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.temper_meltdowns_irritability.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.uncooperative.value \"==\" \"1\"}}uncooperative or disobedient behavior{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.temper_meltdowns_irritability.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.temper_meltdowns_irritability.value \"==\" \"1\"}}temper tantrums or outbursts or meltdowns{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.hits_self.value \"==\" \"1\"}}hitting self{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.bites_self.value \"==\" \"1\"}}biting self{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.injures_self.value \"==\" \"1\"}}hurting self on purpose{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.bangs_head.value \"==\" \"1\"}}banging head{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.irritable.value \"==\" \"1\"}}irritability{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.hits_others.value \"==\" \"1\"}}hitting or kicking others{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.mood_swings.value \"==\" \"1\"}}mood swings{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.aggression.value \"==\" \"1\"}}aggression{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.property_destruction.value \"==\" \"1\"}}property destruction{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.cries_easily.value \"==\" \"1\"}}crying easily or for no reason{{#compare pcori_dsp_interventi.lose_skills.value \"==\" \"1\"}}, {{else}}.{{/compare}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.lose_skills.value \"==\" \"1\"}}loss of previously learned skills.{{/compare}} \n" +
				"                    You also indicated that {{pcori_dsp_interventi.first_name}} has a diagnosis of celiac disease but is not on a gluten-free diet. This may be causing {{pcori_dsp_interventi.first_name}} to experience some of these symptoms. You should talk to {{pcori_dsp_interventi.first_name}}’s doctor about the benefits of a gluten-free diet.\n" +
				"                    </div>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/if}}\n" +
				"    {{/compare}}\n" +
				"    {{/compare}}\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.celiac_test.value \"==\" \"1\"}}\n" +
				"    {{#compare pcori_dsp_interventi.celiac_time.value \"==\" \"1\"}}\n" +
				"    {{#compare pcori_dsp_interventi.celiacd.value \"==\" \"1\"}}\n" +
				"    {{#compare pcori_dsp_interventi.gluten_free.value \"==\" \"1\"}}\n" +
				"    {{#if (or\n" +
				"        (eq pcori_dsp_interventi.hidden_celiac1 \"1\")\n" +
				"        (eq pcori_dsp_interventi.hidden_celiac2 \"1\")\n" +
				"    )}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td>\n" +
				"                    <p><strong>Gluten-free foods.</strong> <a href=\"http://www.massgeneral.org/children/celiac-disease/gi-gluten-free-diet-tips.aspx\" target=\"_blank\">Brief handout {{#i18n \"linkout\"}}{{/i18n}}</a> and <a href=\"http://www.gikids.org/files/documents/resources/Gluten-FreeDietGuideWeb.pdf\" target=\"_blank\">more information on gluten-free foods {{#i18n \"linkout\"}}{{/i18n}}</a>. Also, you can find a wealth of resources from <a href=\"http://www.bidmc.org/Centers-and-Departments/Departments/Digestive-Disease-Center/Services/Celiac-Center/CeliacNow.aspx\" target=\"_blank\">CeliacNow {{#i18n \"linkout\"}}{{/i18n}}</a>. <span class=\"why\">{{#i18n \"why\"}}{{/i18n}}</span></p>\n" +
				"                    <div class=\"well\">You indicated that within the past month, {{pcori_dsp_interventi.first_name}} has experienced \n" +
				"                        {{#compare pcori_dsp_interventi.constipation.value \"==\" \"1\"}}constipation{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.diarrhea.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.nausea.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.vomiting.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.stool.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.incontinence_stool.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.flatulence.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bloating.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.ftt.value \"1\")\n" +
				"                            )}}, {{else}}{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.hyperactivity_adhd.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.inattention.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.impulsivity.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.distracted.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.uncooperative.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.temper_meltdowns_irritability.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.diarrhea.value \"==\" \"1\"}}diarrhea{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.nausea.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.vomiting.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.stool.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.incontinence_stool.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.flatulence.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bloating.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.ftt.value \"1\")\n" +
				"                            )}}, {{else}}{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.hyperactivity_adhd.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.inattention.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.impulsivity.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.distracted.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.uncooperative.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.temper_meltdowns_irritability.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.nausea.value \"==\" \"1\"}}nausea{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.vomiting.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.stool.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.incontinence_stool.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.flatulence.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bloating.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.ftt.value \"1\")\n" +
				"                            )}}, {{else}}{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.hyperactivity_adhd.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.inattention.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.impulsivity.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.distracted.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.uncooperative.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.temper_meltdowns_irritability.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.vomiting.value \"==\" \"1\"}}vomiting{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.stool.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.incontinence_stool.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.flatulence.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bloating.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.ftt.value \"1\")\n" +
				"                            )}}, {{else}}{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.hyperactivity_adhd.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.inattention.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.impulsivity.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.distracted.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.uncooperative.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.temper_meltdowns_irritability.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.stool.value \"==\" \"1\"}}bulky or foul-smelling stools{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.incontinence_stool.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.flatulence.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bloating.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.ftt.value \"1\")\n" +
				"                            )}}, {{else}}{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.hyperactivity_adhd.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.inattention.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.impulsivity.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.distracted.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.uncooperative.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.temper_meltdowns_irritability.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.incontinence_stool.value \"==\" \"1\"}}new accidents with stool{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.flatulence.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bloating.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.ftt.value \"1\")\n" +
				"                            )}}, {{else}}{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.hyperactivity_adhd.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.inattention.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.impulsivity.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.distracted.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.uncooperative.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.temper_meltdowns_irritability.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.flatulence.value \"==\" \"1\"}}passing excessive gas{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.bloating.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.ftt.value \"1\")\n" +
				"                            )}}, {{else}}{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.hyperactivity_adhd.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.inattention.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.impulsivity.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.distracted.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.uncooperative.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.temper_meltdowns_irritability.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.bloating.value \"==\" \"1\"}}bloating{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.ftt.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hyperactivity_adhd.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.inattention.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.impulsivity.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.distracted.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.uncooperative.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.temper_meltdowns_irritability.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.ftt.value \"==\" \"1\"}}unexplained growth failure{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.hyperactivity_adhd.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.inattention.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.impulsivity.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.distracted.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.uncooperative.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.temper_meltdowns_irritability.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.hyperactivity_adhd.value \"==\" \"1\"}}overactive behavior, restlessness, inability to sit still{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.inattention.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.impulsivity.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.distracted.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.uncooperative.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.temper_meltdowns_irritability.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.inattention.value \"==\" \"1\"}}inattention{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.impulsivity.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.distracted.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.uncooperative.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.temper_meltdowns_irritability.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.impulsivity.value \"==\" \"1\"}}impulsivity, acting without thinking{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.distracted.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.uncooperative.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.temper_meltdowns_irritability.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.distracted.value \"==\" \"1\"}}easily distracted{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.uncooperative.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.temper_meltdowns_irritability.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.uncooperative.value \"==\" \"1\"}}uncooperative or disobedient behavior{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.temper_meltdowns_irritability.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.temper_meltdowns_irritability.value \"==\" \"1\"}}temper tantrums or outbursts or meltdowns{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.hits_self.value \"==\" \"1\"}}hitting self{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.bites_self.value \"==\" \"1\"}}biting self{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.injures_self.value \"==\" \"1\"}}hurting self on purpose{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.bangs_head.value \"==\" \"1\"}}banging head{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.irritable.value \"==\" \"1\"}}irritability{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.hits_others.value \"==\" \"1\"}}hitting or kicking others{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.mood_swings.value \"==\" \"1\"}}mood swings{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.aggression.value \"==\" \"1\"}}aggression{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.property_destruction.value \"==\" \"1\"}}property destruction{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.cries_easily.value \"==\" \"1\"}}crying easily or for no reason{{#compare pcori_dsp_interventi.lose_skills.value \"==\" \"1\"}}, {{else}}.{{/compare}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.lose_skills.value \"==\" \"1\"}}loss of previously learned skills.{{/compare}} \n" +
				"                    You also indicated that {{pcori_dsp_interventi.first_name}} has a diagnosis of celiac disease, is on a gluten-free diet, and has had testing for celiac disease done within the past 12 months. You should talk to {{pcori_dsp_interventi.first_name}}’s doctor about these symptoms and explore other reasons for these symptoms.\n" +
				"                    </div>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/if}}\n" +
				"    {{/compare}}\n" +
				"    {{/compare}}\n" +
				"    {{/compare}}\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    <!-- insert sleep apnea handouts -->\n" +
				"\n" +
				"    {{#if (and\n" +
				"        (eq pcori_dsp_interventi.xxxxx.value \"1\")\n" +
				"        (eq pcori_dsp_interventi.xxxxx.value \"1\")\n" +
				"    )}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td>\n" +
				"                    <p><strong>xxxxx.</strong> <a href=\"hxxxxx\" target=\"_blank\">More information on xxxxx. {{#i18n \"linkout\"}}{{/i18n}}</a> <a href=\"hxxxxx\" target=\"_blank\">Evaluation and treatment options [Video]. {{#i18n \"linkout\"}}{{/i18n}}</a> <span class=\"why\">{{#i18n \"why\"}}{{/i18n}}</span></p>\n" +
				"                        <div class=\"well\">Since {{pcori_dsp_interventi.first_name}} xxxxx. If you learn something new, you might consider discussing this handout and webinar with {{pcori_dsp_interventi.first_name}}'s provider.</div>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/if}}\n" +
				"\n" +
				"<!-- End caregiver health wellness resources -->\n" +
				"\n" +
				"    <!-- CAREGIVER LIFESKILLS RESOURCES -->\n" +
				"    <!-- CAREGIVER LIFESKILLS RESOURCES -->\n" +
				"    <!-- CAREGIVER LIFESKILLS RESOURCES -->\n" +
				"\n" +
				"    {{#if (or\n" +
				"        (eq pcori_dsp_interventi.gen_md_phone.value \"2\")\n" +
				"        (eq pcori_dsp_interventi.gen_md_phone.value \"1\")\n" +
				"        (eq pcori_dsp_interventi.gen_md_talk.value \"2\")\n" +
				"        (eq pcori_dsp_interventi.gen_md_talk.value \"1\")\n" +
				"        (eq pcori_dsp_interventi.gen_sx_describe.value \"2\")\n" +
				"        (eq pcori_dsp_interventi.gen_sx_describe.value \"1\")\n" +
				"        (eq pcori_dsp_interventi.gen_meds.value \"2\")\n" +
				"        (eq pcori_dsp_interventi.gen_meds.value \"1\")\n" +
				"        (eq pcori_dsp_interventi.gen_meds_reason.value \"2\")\n" +
				"        (eq pcori_dsp_interventi.gen_meds_reason.value \"1\")\n" +
				"        (eq pcori_dsp_interventi.gen_meds_take.value \"2\")\n" +
				"        (eq pcori_dsp_interventi.gen_meds_take.value \"1\")\n" +
				"        (eq pcori_dsp_interventi.gen_rx.value \"2\")\n" +
				"        (eq pcori_dsp_interventi.gen_rx.value \"1\")\n" +
				"        (eq pcori_dsp_interventi.gen_meds_swallow.value \"2\")\n" +
				"        (eq pcori_dsp_interventi.gen_meds_swallow.value \"1\")\n" +
				"        (eq pcori_dsp_interventi.well_foods.value \"2\")\n" +
				"        (eq pcori_dsp_interventi.well_foods.value \"1\")\n" +
				"        (eq pcori_dsp_interventi.well_habits.value \"2\")\n" +
				"    )}} \n" +
				"        <hr></hr>\n" +
				"        <h2>Life Skills Suggestions for {{pcori_dsp_interventi.first_name}}</h2>\n" +
				"        <h3>One step at a time, {{pcori_dsp_interventi.first_name}} can become increasingly more independent.  Based on your responses, here are some specific resources to help achieve those life skills that you wanted {{pcori_dsp_interventi.first_name}} to work on next.</h3>\n" +
				"        <h3>For more information on transitions related topics, please visit this excellent <a href=\"http://www.gottransition.org/\" target=\"_blank\">“got transitions” website</a>, or <a href=\"http://www.gottransition.org/resources/index.cfm\" target=\"_blank\">this resource specific website</a>, or <a href=\"http://bit.ly/2ukyTGG\" target=\"_blank\">this book</a>.</h3>\n" +
				"    {{else}} {{#if (or\n" +
				"        (eq pcori_dsp_interventi.well_habits.value \"1\")\n" +
				"        (eq pcori_dsp_interventi.well_911.value \"2\")\n" +
				"        (eq pcori_dsp_interventi.well_911.value \"1\")\n" +
				"        (eq pcori_dsp_interventi.well_sleep.value \"2\")\n" +
				"        (eq pcori_dsp_interventi.well_sleep.value \"1\")\n" +
				"        (eq pcori_dsp_interventi.well_exercise.value \"2\")\n" +
				"        (eq pcori_dsp_interventi.well_exercise.value \"1\")\n" +
				"        (eq pcori_dsp_interventi.gen_id.value \"2\")\n" +
				"        (eq pcori_dsp_interventi.gen_id.value \"1\")\n" +
				"        (eq pcori_dsp_interventi.gen_stranger.value \"2\")\n" +
				"        (eq pcori_dsp_interventi.gen_stranger.value \"1\")\n" +
				"        (eq pcori_dsp_interventi.self_public_transport.value \"2\")\n" +
				"        (eq pcori_dsp_interventi.self_public_transport.value \"1\")\n" +
				"        (eq pcori_dsp_interventi.self_chores.value \"2\")\n" +
				"        (eq pcori_dsp_interventi.self_chores.value \"1\")\n" +
				"        (eq pcori_dsp_interventi.self_period.value \"2\")\n" +
				"        (eq pcori_dsp_interventi.self_period.value \"1\")\n" +
				"        (eq pcori_dsp_interventi.self_privacy.value \"2\")\n" +
				"        (eq pcori_dsp_interventi.self_privacy.value \"1\")\n" +
				"    )}} \n" +
				"        <hr></hr>\n" +
				"        <h2>Life Skills Suggestions for {{pcori_dsp_interventi.first_name}}</h2>\n" +
				"        <h3>One step at a time, {{pcori_dsp_interventi.first_name}} can become increasingly more independent.  Based on your responses, here are some specific resources to help achieve those life skills that you wanted {{pcori_dsp_interventi.first_name}} to work on next.</h3>\n" +
				"        <h3>For more information on transitions related topics, please visit this excellent <a href=\"http://www.gottransition.org/\" target=\"_blank\">“got transitions” website</a>, or <a href=\"http://www.gottransition.org/resources/index.cfm\" target=\"_blank\">this resource specific website</a>, or <a href=\"http://bit.ly/2ukyTGG\" target=\"_blank\">this book</a>.</h3>\n" +
				"    {{else}} {{#if (or\n" +
				"        (eq pcori_dsp_interventi.self_dentist.value \"2\")\n" +
				"        (eq pcori_dsp_interventi.self_dentist.value \"1\")\n" +
				"        (eq pcori_dsp_interventi.self_restroom.value \"2\")\n" +
				"        (eq pcori_dsp_interventi.self_restroom.value \"1\")\n" +
				"        (eq pcori_dsp_interventi.self_dress.value \"2\")\n" +
				"        (eq pcori_dsp_interventi.self_dress.value \"1\")\n" +
				"        (eq pcori_dsp_interventi.self_bathe.value \"2\")\n" +
				"        (eq pcori_dsp_interventi.self_bathe.value \"1\")\n" +
				"        (eq pcori_dsp_interventi.self_meals.value \"2\")\n" +
				"        (eq pcori_dsp_interventi.self_meals.value \"1\")\n" +
				"        (eq pcori_dsp_interventi.self_laundry.value \"2\")\n" +
				"        (eq pcori_dsp_interventi.self_laundry.value \"1\")\n" +
				"        (eq pcori_dsp_interventi.know_insurance.value \"2\")\n" +
				"        (eq pcori_dsp_interventi.know_insurance.value \"1\")\n" +
				"        (eq pcori_dsp_interventi.know_plan.value \"2\")\n" +
				"        (eq pcori_dsp_interventi.know_plan.value \"1\")\n" +
				"        (eq pcori_dsp_interventi.know_adults.value \"2\")\n" +
				"        (eq pcori_dsp_interventi.know_adults.value \"1\")\n" +
				"    )}}\n" +
				"        <hr></hr>\n" +
				"        <h2>Life Skills Suggestions for {{pcori_dsp_interventi.first_name}}</h2>\n" +
				"        <h3>One step at a time, {{pcori_dsp_interventi.first_name}} can become increasingly more independent.  Based on your responses, here are some specific resources to help achieve those life skills that you wanted {{pcori_dsp_interventi.first_name}} to work on next.</h3>\n" +
				"        <h3>For more information on transitions related topics, please visit this excellent <a href=\"http://www.gottransition.org/\" target=\"_blank\">“got transitions” website</a>, or <a href=\"http://www.gottransition.org/resources/index.cfm\" target=\"_blank\">this resource specific website</a>, or <a href=\"http://bit.ly/2ukyTGG\" target=\"_blank\">this book</a>.</h3>\n" +
				"    {{/if}}{{/if}}{{/if}}\n" +
				"\n" +
				"        {{#if (or\n" +
				"        (eq pcori_dsp_interventi.gen_md_phone.value \"2\")\n" +
				"        (eq pcori_dsp_interventi.gen_md_talk.value \"2\")\n" +
				"        (eq pcori_dsp_interventi.gen_sx_describe.value \"2\")\n" +
				"        (eq pcori_dsp_interventi.gen_meds.value \"2\")\n" +
				"        (eq pcori_dsp_interventi.gen_meds_reason.value \"2\")\n" +
				"        (eq pcori_dsp_interventi.gen_meds_take.value \"2\")\n" +
				"        (eq pcori_dsp_interventi.gen_rx.value \"2\")\n" +
				"        (eq pcori_dsp_interventi.gen_meds_swallow.value \"2\")\n" +
				"        (eq pcori_dsp_interventi.well_foods.value \"2\")\n" +
				"        (eq pcori_dsp_interventi.well_habits.value \"2\")\n" +
				"    )}} \n" +
				"        <h3><strong>Goals for {{pcori_dsp_interventi.first_name}} to work on now:</strong></h3>\n" +
				"    {{else}} {{#if (or\n" +
				"        (eq pcori_dsp_interventi.well_911.value \"2\")\n" +
				"        (eq pcori_dsp_interventi.well_sleep.value \"2\")\n" +
				"        (eq pcori_dsp_interventi.well_exercise.value \"2\")\n" +
				"        (eq pcori_dsp_interventi.gen_id.value \"2\")\n" +
				"        (eq pcori_dsp_interventi.gen_stranger.value \"2\")\n" +
				"        (eq pcori_dsp_interventi.self_public_transport.value \"2\")\n" +
				"        (eq pcori_dsp_interventi.self_chores.value \"2\")\n" +
				"        (eq pcori_dsp_interventi.self_period.value \"2\")\n" +
				"        (eq pcori_dsp_interventi.self_privacy.value \"2\")\n" +
				"        (eq pcori_dsp_interventi.self_dentist.value \"2\")\n" +
				"        (eq pcori_dsp_interventi.self_restroom.value \"2\")\n" +
				"        (eq pcori_dsp_interventi.self_dress.value \"2\")\n" +
				"        (eq pcori_dsp_interventi.self_bathe.value \"2\")\n" +
				"        (eq pcori_dsp_interventi.self_meals.value \"2\")\n" +
				"        (eq pcori_dsp_interventi.self_laundry.value \"2\")\n" +
				"        (eq pcori_dsp_interventi.know_insurance.value \"2\")\n" +
				"        (eq pcori_dsp_interventi.know_plan.value \"2\")\n" +
				"        (eq pcori_dsp_interventi.know_adults.value \"2\")\n" +
				"    )}}\n" +
				"        <h3><strong>Goals for {{pcori_dsp_interventi.first_name}} to work on now:</strong></h3>\n" +
				"    {{/if}}{{/if}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.gen_md_phone.value \"==\" \"2\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td>\n" +
				"                    <p>To learn <strong>where to find her doctor’s phone number</strong>. <a href=\"!!!!!\" target=\"_blank\">Here is a resource to help. {{#i18n \"linkout\"}}{{/i18n}}</a></p>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.gen_md_talk.value \"==\" \"2\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td>\n" +
				"                    <p>To learn <strong>how to ask questions of {{pcori_dsp_interventi.hishertheir}} doctor</strong>. <a href=\"http://www.massgeneral.org/children/assets/pdf/down-syndrome-talking-to-the-doctor.pdf\" target=\"_blank\">Here is a resource to help. {{#i18n \"linkout\"}}{{/i18n}}</a></p>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.gen_sx_describe.value \"==\" \"2\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td>\n" +
				"                    <p>To learn <strong>how to describe how {{pcori_dsp_interventi.heshe}} is feeling to {{pcori_dsp_interventi.hishertheir}} doctor</strong>. <a href=\"http://www.massgeneral.org/children/print/down-syndrome-talking-to-the-doctor.pdf\" target=\"_blank\">Here is a resource to help. {{#i18n \"linkout\"}}{{/i18n}}</a></p>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.gen_meds.value \"==\" \"2\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td>\n" +
				"                    <p>To learn <strong>how to find {{pcori_dsp_interventi.hishertheir}} medications</strong>. <a href=\"!!!!!\" target=\"_blank\">Here is a resource to help. {{#i18n \"linkout\"}}{{/i18n}}</a></p>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.gen_meds_reason.value \"==\" \"2\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td>\n" +
				"                    <p>To learn <strong>what each of {{pcori_dsp_interventi.hishertheir}} medications is for</strong>. <a href=\"!!!!!\" target=\"_blank\">Here is a resource to help. {{#i18n \"linkout\"}}{{/i18n}}</a></p>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.gen_meds_take.value \"==\" \"2\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td>\n" +
				"                    <p>To learn <strong>how to take {{pcori_dsp_interventi.hishertheir}} medications every day on {{pcori_dsp_interventi.hishertheir}} own</strong>. <a href=\"!!!!!\" target=\"_blank\">Here is a resource to help. {{#i18n \"linkout\"}}{{/i18n}}</a></p>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.gen_rx.value \"==\" \"2\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td>\n" +
				"                    <p>To learn <strong>how to refill {{pcori_dsp_interventi.hishertheir}} prescriptions on {{pcori_dsp_interventi.hishertheir}} own</strong>. <a href=\"!!!!!\" target=\"_blank\">Here is a resource to help. {{#i18n \"linkout\"}}{{/i18n}}</a></p>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.gen_meds_swallow.value \"==\" \"2\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td>\n" +
				"                    <p>To learn <strong>how to swallow whole pills</strong>. <a href=\"!!!!!\" target=\"_blank\">Here is a resource to help. {{#i18n \"linkout\"}}{{/i18n}}</a></p>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.well_foods.value \"==\" \"2\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td>\n" +
				"                    <p>To learn about <strong>the differences between healthy and unhealthy foods</strong>. <a href=\"http://www.massgeneral.org/children/print/down-syndrome-healthy-lifestyle-story.pdf\" target=\"_blank\">Here is a resource to help. {{#i18n \"linkout\"}}{{/i18n}}</a></p>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.well_habits.value \"==\" \"2\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td>\n" +
				"                    <p>To learn about <strong>the risks of alcohol, drugs, and tobacco use</strong>. <a href=\"!!!!!\" target=\"_blank\">Here is a resource to help. {{#i18n \"linkout\"}}{{/i18n}}</a></p>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.well_911.value \"==\" \"2\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td>\n" +
				"                    <p>To learn <strong>how to call 911 if there is an emergency</strong>. <a href=\"http://www.massgeneral.org/children/print/down-syndrome-emergency-booklet.pdf\" target=\"_blank\">Here is a resource to help. {{#i18n \"linkout\"}}{{/i18n}}</a></p>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.well_sleep.value \"==\" \"2\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td>\n" +
				"                    <p>To be able to <strong>sleep 7 to 8 hours per night</strong>. <a href=\"!!!!!\" target=\"_blank\">Here is a resource to help. {{#i18n \"linkout\"}}{{/i18n}}</a></p>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.well_exercise.value \"==\" \"2\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td>\n" +
				"                    <p>To <strong>exercise regularly</strong>. <a href=\"http://www.massgeneral.org/children/print/down-syndrome-healthy-lifestyle-story.pdf\" target=\"_blank\">Here is a resource to help. {{#i18n \"linkout\"}}{{/i18n}}</a></p>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.gen_id.value \"==\" \"2\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td>\n" +
				"                    <p>To learn <strong>how to provide {{pcori_dsp_interventi.hishertheir}} personal information when needed</strong>. <a href=\"http://www.massgeneral.org/children/print/down-syndrome-personal-information.pdf\" target=\"_blank\">Here is a resource to help. {{#i18n \"linkout\"}}{{/i18n}}</a></p>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.gen_stranger.value \"==\" \"2\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td>\n" +
				"                    <p>To learn <strong>tell the difference between a stranger and a friend</strong>. <a href=\"http://www.massgeneral.org/children/print/down-syndrome-strangers-and-friends.pdf\" target=\"_blank\">Here is a resource to help. {{#i18n \"linkout\"}}{{/i18n}}</a></p>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.self_public_transport.value \"==\" \"2\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td>\n" +
				"                    <p>To learn <strong>how to use public transportation on {{pcori_dsp_interventi.hishertheir}} own</strong>. <a href=\"http://www.massgeneral.org/children/print/down-syndrome-public-transport.pdf\" target=\"_blank\">Here is a resource to help. {{#i18n \"linkout\"}}{{/i18n}}</a> We’d also recommend that you read <a href=\"http://bit.ly/2ukyTGG\" target=\"_blank\">pages 231-234 of this book {{#i18n \"linkout\"}}{{/i18n}}</a>.</p>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.self_chores.value \"==\" \"2\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td>\n" +
				"                    <p>To learn <strong>how to do household chores</strong>. <a href=\"http://www.massgeneral.org/children/print/down-syndrome-household-chores.pdf\" target=\"_blank\">Here is a resource to help. {{#i18n \"linkout\"}}{{/i18n}}</a></p>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.self_period.value \"==\" \"2\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td>\n" +
				"                    <p>To learn <strong>how to manage her period</strong>. <a href=\"!!!!!\" target=\"_blank\">Here is a resource to help. {{#i18n \"linkout\"}}{{/i18n}}</a></p>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.self_privacy.value \"==\" \"2\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td>\n" +
				"                    <p>To <strong>understand sexual boundaries and privacy</strong>. <a href=\"http://bit.ly/2ufwQUL\" target=\"_blank\">Here is a great book for caregivers. {{#i18n \"linkout\"}}{{/i18n}}</a> <a href=\"http://bit.ly/2tkV2At\" target=\"_blank\">Here is a guide to dating for people with Down syndrome. {{#i18n \"linkout\"}}{{/i18n}}</a> Also, consider the <a href=\"http://www.lesliewalker-hirsch.com/publications/\" target=\"_blank\">Circles&#174; curriculum {{#i18n \"linkout\"}}{{/i18n}}</a>, also available as the <a href=\"http://circlesapp.com/\" target=\"_blank\">Circles App&#8482; {{#i18n \"linkout\"}}{{/i18n}}</a>.</p>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.self_dentist.value \"==\" \"2\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td>\n" +
				"                    <p>To learn <strong>how to brush {{pcori_dsp_interventi.hishertheir}} teeth on {{pcori_dsp_interventi.hishertheir}} own</strong>. <a href=\"http://www.massgeneral.org/children/print/down-syndrome-brushing-teeth-tips.pdf\" target=\"_blank\">Here is a resource to help. {{#i18n \"linkout\"}}{{/i18n}}</a></p>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.self_restroom.value \"==\" \"2\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td>\n" +
				"                    <p>To be able to <strong>use a public restroom on {{pcori_dsp_interventi.hishertheir}} own</strong>. <a href=\"!!!!!\" target=\"_blank\">Here is a resource to help. {{#i18n \"linkout\"}}{{/i18n}}</a></p>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.self_dress.value \"==\" \"2\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td>\n" +
				"                    <p>To be able to <strong>dress {{pcori_dsp_interventi.himherthem}}self</strong>. <a href=\"!!!!!\" target=\"_blank\">Here is a resource to help. {{#i18n \"linkout\"}}{{/i18n}}</a></p>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.self_bathe.value \"==\" \"2\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td>\n" +
				"                    <p>To be able to <strong>bathe/shower {{pcori_dsp_interventi.himherthem}}self</strong>. <a href=\"http://www.massgeneral.org/children/print/down-syndrome-bathing-and-showering.pdf\" target=\"_blank\">Here is a resource to help. {{#i18n \"linkout\"}}{{/i18n}}</a></p>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.self_meals.value \"==\" \"2\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td>\n" +
				"                    <p>To be able to <strong>prepare {{pcori_dsp_interventi.hishertheir}} own meals</strong>. <a href=\"http://downsyndromenutrition.com/tools/books/79-cooking-by-color\" target=\"_blank\">Here is a cookbook designed specifically for people with Down syndrome. {{#i18n \"linkout\"}}{{/i18n}}</a></p>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.self_laundry.value \"==\" \"2\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td>\n" +
				"                    <p>To be able to <strong>do {{pcori_dsp_interventi.hishertheir}} own laundry</strong>. <a href=\"!!!!!\" target=\"_blank\">Here is a resource to help. {{#i18n \"linkout\"}}{{/i18n}}</a></p>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.know_insurance.value \"==\" \"2\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td>\n" +
				"                    <p>To <strong>find {{pcori_dsp_interventi.hishertheir}} insurance card</strong>. <a href=\"!!!!!\" target=\"_blank\">Here is a resource to help. {{#i18n \"linkout\"}}{{/i18n}}</a></p>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.know_plan.value \"==\" \"2\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td>\n" +
				"                    <p>To <strong>have a plan for what {{pcori_dsp_interventi.heshe}} will do after finishing high school</strong>. Consider reading <a href=\"https://www.woodbinehouse.com/main.asp?product_id=978-1-890627-87-4\" target=\"_blank\">pages 53-73 of this book {{#i18n \"linkout\"}}{{/i18n}}</a>. Here is <a href=\"http://www.ndss.org/Resources/Transition-and-Beyond/Life-After-High-School/\" target=\"_blank\">additional information on life after high school {{#i18n \"linkout\"}}{{/i18n}}</a>, and some\n" +
				"                    <a href=\"http://www.thinkcollege.net/\" target=\"_blank\">college options {{#i18n \"linkout\"}}{{/i18n}}</a>.</p>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.know_adults.value \"==\" \"2\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td>\n" +
				"                    <p>To <strong>name at least two adults {{pcori_dsp_interventi.heshe}} can ask for help when needed</strong>. <a href=\"!!!!!\" target=\"_blank\">Here is a resource to help. {{#i18n \"linkout\"}}{{/i18n}}</a></p>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#if (or\n" +
				"        (eq pcori_dsp_interventi.gen_md_phone.value \"1\")\n" +
				"        (eq pcori_dsp_interventi.gen_md_talk.value \"1\")\n" +
				"        (eq pcori_dsp_interventi.gen_sx_describe.value \"1\")\n" +
				"        (eq pcori_dsp_interventi.gen_meds.value \"1\")\n" +
				"        (eq pcori_dsp_interventi.gen_meds_reason.value \"1\")\n" +
				"        (eq pcori_dsp_interventi.gen_meds_take.value \"1\")\n" +
				"        (eq pcori_dsp_interventi.gen_rx.value \"1\")\n" +
				"        (eq pcori_dsp_interventi.gen_meds_swallow.value \"1\")\n" +
				"        (eq pcori_dsp_interventi.well_foods.value \"1\")\n" +
				"        (eq pcori_dsp_interventi.well_habits.value \"1\")\n" +
				"    )}} \n" +
				"        <h3><strong>Goals for {{pcori_dsp_interventi.first_name}} to work on later:</strong></h3>\n" +
				"    {{else}} {{#if (or\n" +
				"        (eq pcori_dsp_interventi.well_911.value \"1\")\n" +
				"        (eq pcori_dsp_interventi.well_sleep.value \"1\")\n" +
				"        (eq pcori_dsp_interventi.well_exercise.value \"1\")\n" +
				"        (eq pcori_dsp_interventi.gen_id.value \"1\")\n" +
				"        (eq pcori_dsp_interventi.gen_stranger.value \"1\")\n" +
				"        (eq pcori_dsp_interventi.self_public_transport.value \"1\")\n" +
				"        (eq pcori_dsp_interventi.self_chores.value \"1\")\n" +
				"        (eq pcori_dsp_interventi.self_period.value \"1\")\n" +
				"        (eq pcori_dsp_interventi.self_privacy.value \"1\")\n" +
				"        (eq pcori_dsp_interventi.self_dentist.value \"1\")\n" +
				"        (eq pcori_dsp_interventi.self_restroom.value \"1\")\n" +
				"        (eq pcori_dsp_interventi.self_dress.value \"1\")\n" +
				"        (eq pcori_dsp_interventi.self_bathe.value \"1\")\n" +
				"        (eq pcori_dsp_interventi.self_meals.value \"1\")\n" +
				"        (eq pcori_dsp_interventi.self_laundry.value \"1\")\n" +
				"        (eq pcori_dsp_interventi.know_insurance.value \"1\")\n" +
				"        (eq pcori_dsp_interventi.know_plan.value \"1\")\n" +
				"        (eq pcori_dsp_interventi.know_adults.value \"1\")\n" +
				"    )}}\n" +
				"        <h3><strong>Goals for {{pcori_dsp_interventi.first_name}} to work on later:</strong></h3>\n" +
				"    {{/if}}{{/if}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.gen_md_phone.value \"==\" \"1\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td>\n" +
				"                    <p>To learn <strong>where to find her doctor’s phone number</strong>. <a href=\"!!!!!\" target=\"_blank\">Here is a resource to help. {{#i18n \"linkout\"}}{{/i18n}}</a></p>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.gen_md_talk.value \"==\" \"1\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td>\n" +
				"                    <p>To learn <strong>how to ask questions of {{pcori_dsp_interventi.hishertheir}} doctor</strong>. <a href=\"http://www.massgeneral.org/children/assets/pdf/down-syndrome-talking-to-the-doctor.pdf\" target=\"_blank\">Here is a resource to help. {{#i18n \"linkout\"}}{{/i18n}}</a></p>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.gen_sx_describe.value \"==\" \"1\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td>\n" +
				"                    <p>To learn <strong>how to describe how {{pcori_dsp_interventi.heshe}} is feeling to {{pcori_dsp_interventi.hishertheir}} doctor</strong>. <a href=\"http://www.massgeneral.org/children/print/down-syndrome-talking-to-the-doctor.pdf\" target=\"_blank\">Here is a resource to help. {{#i18n \"linkout\"}}{{/i18n}}</a></p>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.gen_meds.value \"==\" \"1\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td>\n" +
				"                    <p>To learn <strong>how to find {{pcori_dsp_interventi.hishertheir}} medications</strong>. <a href=\"!!!!!\" target=\"_blank\">Here is a resource to help. {{#i18n \"linkout\"}}{{/i18n}}</a></p>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.gen_meds_reason.value \"==\" \"1\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td>\n" +
				"                    <p>To learn <strong>what each of {{pcori_dsp_interventi.hishertheir}} medications is for</strong>. <a href=\"!!!!!\" target=\"_blank\">Here is a resource to help. {{#i18n \"linkout\"}}{{/i18n}}</a></p>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.gen_meds_take.value \"==\" \"1\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td>\n" +
				"                    <p>To learn <strong>how to take {{pcori_dsp_interventi.hishertheir}} medications every day on {{pcori_dsp_interventi.hishertheir}} own</strong>. <a href=\"!!!!!\" target=\"_blank\">Here is a resource to help. {{#i18n \"linkout\"}}{{/i18n}}</a></p>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.gen_rx.value \"==\" \"1\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td>\n" +
				"                    <p>To learn <strong>how to refill {{pcori_dsp_interventi.hishertheir}} prescriptions on {{pcori_dsp_interventi.hishertheir}} own</strong>. <a href=\"!!!!!\" target=\"_blank\">Here is a resource to help. {{#i18n \"linkout\"}}{{/i18n}}</a></p>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.gen_meds_swallow.value \"==\" \"1\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td>\n" +
				"                    <p>To learn <strong>how to swallow whole pills</strong>. <a href=\"!!!!!\" target=\"_blank\">Here is a resource to help. {{#i18n \"linkout\"}}{{/i18n}}</a></p>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.well_foods.value \"==\" \"1\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td>\n" +
				"                    <p>To learn about <strong>the differences between healthy and unhealthy foods</strong>. <a href=\"http://www.massgeneral.org/children/print/down-syndrome-healthy-lifestyle-story.pdf\" target=\"_blank\">Here is a resource to help. {{#i18n \"linkout\"}}{{/i18n}}</a></p>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.well_habits.value \"==\" \"1\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td>\n" +
				"                    <p>To learn about <strong>the risks of alcohol, drugs, and tobacco use</strong>. <a href=\"!!!!!\" target=\"_blank\">Here is a resource to help. {{#i18n \"linkout\"}}{{/i18n}}</a></p>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.well_911.value \"==\" \"1\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td>\n" +
				"                    <p>To learn <strong>how to call 911 if there is an emergency</strong>. <a href=\"http://www.massgeneral.org/children/print/down-syndrome-emergency-booklet.pdf\" target=\"_blank\">Here is a resource to help. {{#i18n \"linkout\"}}{{/i18n}}</a></p>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.well_sleep.value \"==\" \"1\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td>\n" +
				"                    <p>To be able to <strong>sleep 7 to 8 hours per night</strong>. <a href=\"!!!!!\" target=\"_blank\">Here is a resource to help. {{#i18n \"linkout\"}}{{/i18n}}</a></p>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.well_exercise.value \"==\" \"1\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td>\n" +
				"                    <p>To <strong>exercise regularly</strong>. <a href=\"http://www.massgeneral.org/children/print/down-syndrome-healthy-lifestyle-story.pdf\" target=\"_blank\">Here is a resource to help. {{#i18n \"linkout\"}}{{/i18n}}</a></p>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.gen_id.value \"==\" \"1\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td>\n" +
				"                    <p>To learn <strong>how to provide {{pcori_dsp_interventi.hishertheir}} personal information when needed</strong>. <a href=\"http://www.massgeneral.org/children/print/down-syndrome-personal-information.pdf\" target=\"_blank\">Here is a resource to help. {{#i18n \"linkout\"}}{{/i18n}}</a></p>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.gen_stranger.value \"==\" \"1\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td>\n" +
				"                    <p>To learn <strong>tell the difference between a stranger and a friend</strong>. <a href=\"http://www.massgeneral.org/children/print/down-syndrome-strangers-and-friends.pdf\" target=\"_blank\">Here is a resource to help. {{#i18n \"linkout\"}}{{/i18n}}</a></p>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.self_public_transport.value \"==\" \"1\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td>\n" +
				"                    <p>To learn <strong>how to use public transportation on {{pcori_dsp_interventi.hishertheir}} own</strong>. <a href=\"http://www.massgeneral.org/children/print/down-syndrome-public-transport.pdf\" target=\"_blank\">Here is a resource to help. {{#i18n \"linkout\"}}{{/i18n}}</a> We’d also recommend that you read <a href=\"http://bit.ly/2ukyTGG\" target=\"_blank\">pages 231-234 of this book {{#i18n \"linkout\"}}{{/i18n}}</a>.</p>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.self_chores.value \"==\" \"1\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td>\n" +
				"                    <p>To learn <strong>how to do household chores</strong>. <a href=\"http://www.massgeneral.org/children/print/down-syndrome-household-chores.pdf\" target=\"_blank\">Here is a resource to help. {{#i18n \"linkout\"}}{{/i18n}}</a></p>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.self_period.value \"==\" \"1\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td>\n" +
				"                    <p>To learn <strong>how to manage her period</strong>. <a href=\"!!!!!\" target=\"_blank\">Here is a resource to help. {{#i18n \"linkout\"}}{{/i18n}}</a></p>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.self_privacy.value \"==\" \"1\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td>\n" +
				"                    <p>To <strong>understand sexual boundaries and privacy</strong>. <a href=\"http://bit.ly/2ufwQUL\" target=\"_blank\">Here is a great book for caregivers. {{#i18n \"linkout\"}}{{/i18n}}</a> <a href=\"http://bit.ly/2tkV2At\" target=\"_blank\">Here is a guide to dating for people with Down syndrome. {{#i18n \"linkout\"}}{{/i18n}}</a> Also, consider the <a href=\"http://www.lesliewalker-hirsch.com/publications/\" target=\"_blank\">Circles&reg; curriculum {{#i18n \"linkout\"}}{{/i18n}}</a>, also available as the <a href=\"http://circlesapp.com/\" target=\"_blank\">Circles App&trade; {{#i18n \"linkout\"}}{{/i18n}}</a>.</p>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.self_dentist.value \"==\" \"1\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td>\n" +
				"                    <p>To learn <strong>how to brush {{pcori_dsp_interventi.hishertheir}} teeth on {{pcori_dsp_interventi.hishertheir}} own</strong>. <a href=\"http://www.massgeneral.org/children/print/down-syndrome-brushing-teeth-tips.pdf\" target=\"_blank\">Here is a resource to help. {{#i18n \"linkout\"}}{{/i18n}}</a></p>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.self_restroom.value \"==\" \"1\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td>\n" +
				"                    <p>To be able to <strong>use a public restroom on {{pcori_dsp_interventi.hishertheir}} own</strong>. <a href=\"!!!!!\" target=\"_blank\">Here is a resource to help. {{#i18n \"linkout\"}}{{/i18n}}</a></p>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.self_dress.value \"==\" \"1\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td>\n" +
				"                    <p>To be able to <strong>dress {{pcori_dsp_interventi.himherthem}}self</strong>. <a href=\"!!!!!\" target=\"_blank\">Here is a resource to help. {{#i18n \"linkout\"}}{{/i18n}}</a></p>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.self_bathe.value \"==\" \"1\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td>\n" +
				"                    <p>To be able to <strong>bathe/shower {{pcori_dsp_interventi.himherthem}}self</strong>. <a href=\"http://www.massgeneral.org/children/print/down-syndrome-bathing-and-showering.pdf\" target=\"_blank\">Here is a resource to help. {{#i18n \"linkout\"}}{{/i18n}}</a></p>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.self_meals.value \"==\" \"1\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td>\n" +
				"                    <p>To be able to <strong>prepare {{pcori_dsp_interventi.hishertheir}} own meals</strong>. <a href=\"http://downsyndromenutrition.com/tools/books/79-cooking-by-color\" target=\"_blank\">Here is a cookbook designed specifically for people with Down syndrome. {{#i18n \"linkout\"}}{{/i18n}}</a></p>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.self_laundry.value \"==\" \"1\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td>\n" +
				"                    <p>To be able to <strong>do {{pcori_dsp_interventi.hishertheir}} own laundry</strong>. <a href=\"!!!!!\" target=\"_blank\">Here is a resource to help. {{#i18n \"linkout\"}}{{/i18n}}</a></p>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.know_insurance.value \"==\" \"1\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td>\n" +
				"                    <p>To <strong>find {{pcori_dsp_interventi.hishertheir}} insurance card</strong>. <a href=\"!!!!!\" target=\"_blank\">Here is a resource to help. {{#i18n \"linkout\"}}{{/i18n}}</a></p>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.know_plan.value \"==\" \"1\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td>\n" +
				"                    <p>To <strong>have a plan for what {{pcori_dsp_interventi.heshe}} will do after finishing high school</strong>. Consider reading <a href=\"https://www.woodbinehouse.com/main.asp?product_id=978-1-890627-87-4\" target=\"_blank\">pages 53-73 of this book {{#i18n \"linkout\"}}{{/i18n}}</a>. Here is <a href=\"http://www.ndss.org/Resources/Transition-and-Beyond/Life-After-High-School/\" target=\"_blank\">additional information on life after high school {{#i18n \"linkout\"}}{{/i18n}}</a>, and some\n" +
				"                    <a href=\"http://www.thinkcollege.net/\" target=\"_blank\">college options {{#i18n \"linkout\"}}{{/i18n}}</a>.</p>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.know_adults.value \"==\" \"1\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td>\n" +
				"                    <p>To <strong>name at least two adults {{pcori_dsp_interventi.heshe}} can ask for help when needed</strong>. <a href=\"!!!!!\" target=\"_blank\">Here is a resource to help. {{#i18n \"linkout\"}}{{/i18n}}</a></p>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"<!-- End caregiver lifeskills resources -->\n" +
				"\n" +
				"\n" +
				"\n" +
				"    <!-- CAREGIVER RESOURCES -->\n" +
				"    <!-- CAREGIVER RESOURCES -->\n" +
				"    <!-- CAREGIVER RESOURCES -->\n" +
				"\n" +
				"    <hr></hr>\n" +
				"    <h2>Information and Resources for {{pcori_dsp_interventi.first_name}}'s Caregiver</h2>\n" +
				"    <h3>Here are some specific resources based on your requests.</h3>\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.adrc.value \"==\" \"1\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td>\n" +
				"                    <p><strong>Aging and Disability Resource Consortia (ADRC).</strong> ADRCs offer coordinated systems of information and access to long-term services and supports regardless of disability, age, or income, starting at age 21. Since ADRCs vary from state to state, we recommend that you use Google to search for the ADRC in your state, by entering “ADRC” and the name of your state, to find your local organization. <span class=\"why\">{{#i18n \"why\"}}{{/i18n}}</span></p>\n" +
				"                    <div class=\"well\">You requested more information on ADRC (Aging and Disability Resource Consortia).</div>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.arc.value \"==\" \"1\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td>\n" +
				"                    <p><strong>The Arc.</strong> <a href=\"http://www.thearc.org/\" target=\"_blank\">More information on their webpage. {{#i18n \"linkout\"}}{{/i18n}}</a> <a href=\"http://www.thearc.org/find-a-chapter\" target=\"_blank\">Find your local Arc chapter. {{#i18n \"linkout\"}}{{/i18n}}</a> <span class=\"why\">{{#i18n \"why\"}}{{/i18n}}</span></p>\n" +
				"                    <div class=\"well\">You requested more information on the Arc.</div>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.bestbuds.value \"==\" \"1\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td>\n" +
				"                    <p><strong>Best Buddies.</strong> <a href=\"https://bestbuddies.org/\" target=\"_blank\">More information on their webpage. {{#i18n \"linkout\"}}{{/i18n}}</a> <a href=\"https://bestbuddies.org/u-s-programs/\" target=\"_blank\">Find programs in your state. {{#i18n \"linkout\"}}{{/i18n}}</a> <span class=\"why\">{{#i18n \"why\"}}{{/i18n}}</span></p>\n" +
				"                    <div class=\"well\">You requested more information on Best Buddies.</div>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.dsconnect.value \"==\" \"1\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td>\n" +
				"                    <p><strong>DS-Connect&reg;.</strong> <a href=\"https://dsconnect.nih.gov/\" target=\"_blank\">Consider registering {{pcori_dsp_interventi.first_name}} at DS-Connect {{#i18n \"linkout\"}}{{/i18n}}</a> to connect with researchers and healthcare providers. <span class=\"why\">{{#i18n \"why\"}}{{/i18n}}</span></p>\n" +
				"                    <div class=\"well\">You requested more information on DS-Connect&reg;.</div>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.gigi.value \"==\" \"1\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td>\n" +
				"                    <p><strong>GiGi’s Playhouse.</strong> <a href=\"https://gigisplayhouse.org/\" target=\"_blank\">More information on their webpage. {{#i18n \"linkout\"}}{{/i18n}}</a> <a href=\"https://gigisplayhouse.org/locations\" target=\"_blank\">Find locations near you. {{#i18n \"linkout\"}}{{/i18n}}</a> <span class=\"why\">{{#i18n \"why\"}}{{/i18n}}</span></p>\n" +
				"                    <div class=\"well\">You requested more information on GiGi’s Playhouse.</div>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.gdsf.value \"==\" \"1\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td>\n" +
				"                    <p><strong>Global Down Syndrome Foundation.</strong> <a href=\"http://www.globaldownsyndrome.org/\" target=\"_blank\">More information on their webpage. {{#i18n \"linkout\"}}{{/i18n}}</a> <a href=\"http://www.globaldownsyndrome.org/mailing-list/\" target=\"_blank\">Join their mailing list. {{#i18n \"linkout\"}}{{/i18n}}</a> <span class=\"why\">{{#i18n \"why\"}}{{/i18n}}</span></p>\n" +
				"                    <div class=\"well\">You requested more information on the Global Down Syndrome Foundation.</div>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.lumind.value \"==\" \"1\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td>\n" +
				"                    <p><strong>LuMind Research Down Syndrome Foundation.</strong> <a href=\"https://www.lumindrds.org/\" target=\"_blank\">More information on their webpage. {{#i18n \"linkout\"}}{{/i18n}}</a> <a href=\"http://bit.ly/2sOjHCp\" target=\"_blank\">Join their mailing list. {{#i18n \"linkout\"}}{{/i18n}}</a> <span class=\"why\">{{#i18n \"why\"}}{{/i18n}}</span></p>\n" +
				"                    <div class=\"well\">You requested more information on LuMind Research Down Syndrome Foundation.</div>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.ndsc.value \"==\" \"1\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td>\n" +
				"                    <p><strong>National Down Syndrome Congress (NDSC).</strong> <a href=\"https://www.ndsccenter.org/\" target=\"_blank\">More information on their webpage. {{#i18n \"linkout\"}}{{/i18n}}</a> <a href=\"https://www.ndsccenter.org/stay-up-to-date-with-ndsc-news/\" target=\"_blank\">Join their mailing list. {{#i18n \"linkout\"}}{{/i18n}}</a> <span class=\"why\">{{#i18n \"why\"}}{{/i18n}}</span></p>\n" +
				"                    <div class=\"well\">You requested more information on the National Down Syndrome Congress (NDSC).</div>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.ndss.value \"==\" \"1\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td>\n" +
				"                    <p><strong>National Down Syndrome Society (NDSS).</strong> <a href=\"http://www.ndss.org/\" target=\"_blank\">More information on their webpage. {{#i18n \"linkout\"}}{{/i18n}}</a> <span class=\"why\">{{#i18n \"why\"}}{{/i18n}}</span></p>\n" +
				"                    <div class=\"well\">You requested more information on the National Down Syndrome Society (NDSS).</div>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.specialolympics.value \"==\" \"1\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td>\n" +
				"                    <p><strong>Special Olympics.</strong> <a href=\"http://www.specialolympics.org/\" target=\"_blank\">More information on their webpage. {{#i18n \"linkout\"}}{{/i18n}}</a> <a href=\"http://www.specialolympics.org/Common/Special_Olympics_Program_Locator.aspx\" target=\"_blank\">Find programs near you. {{#i18n \"linkout\"}}{{/i18n}}</a> <span class=\"why\">{{#i18n \"why\"}}{{/i18n}}</span></p>\n" +
				"                    <div class=\"well\">You requested more information on the Special Olympics.</div>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.local.value \"==\" \"2\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td>\n" +
				"                    <p><strong>Local Down syndrome organization.</strong> You can access <a href=\" http://www.dsaia.org/Find-a-Group\" target=\"_blank\">this link {{#i18n \"linkout\"}}{{/i18n}}</a> to find a list of all the Down syndrome organizations in the U.S. Check to see if there might be one near you! <span class=\"why\">{{#i18n \"why\"}}{{/i18n}}</span></p>\n" +
				"                    <div class=\"well\">You indicated that you are not yet connected to a local Down syndrome organization.</div>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.guardianship.value \"==\" \"1\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td>\n" +
				"                    <p><strong>Guardianship options.</strong> Guardianship laws can vary from state to state. However, there are some general guardianship options you might consider for {{pcori_dsp_interventi.first_name}}. Check out pages 92-100 in <a href=\"https://www.woodbinehouse.com/contents.asp?product_id=978-1-890627-87-4\" target=\"_blank\">this book {{#i18n \"linkout\"}}{{/i18n}}</a> for information on guardianship options in the USA. <span class=\"why\">{{#i18n \"why\"}}{{/i18n}}</span></p>\n" +
				"                    <div class=\"well\">You indicated that you would like more information on guardianship options.</div>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.ssi_2.value \"==\" \"1\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td>\n" +
				"                    <p><strong>Social Security Insurance (SSI).</strong> Check out <a href=\"https://www.ssa.gov/disability/Documents/SSA-1170-KIT.pdf\" target=\"_blank\">this starter kit {{#i18n \"linkout\"}}{{/i18n}}</a> for adults with disabilities applying for social security benefits. Also check out <a href=\"http://exceptionallives.org/ssi-guide/\" target=\"_blank\">this website {{#i18n \"linkout\"}}{{/i18n}}</a> for a step by step guide on how to apply for SSI. <span class=\"why\">{{#i18n \"why\"}}{{/i18n}}</span></p>\n" +
				"                    <div class=\"well\">You indicated that you would like more information on social security insurance.</div>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.able_2.value \"==\" \"1\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td>\n" +
				"                    <p><strong>Special Needs Trust/ABLE Accounts.</strong> Check out <a href=\"http://www.nolo.com/legal-encyclopedia/special-needs-trusts-30315.html\" target=\"_blank\">this website {{#i18n \"linkout\"}}{{/i18n}}</a> or <a href=\"https://www.amazon.com/Special-Needs-Planning-Guide-Prepare/dp/1557668027\" target=\"_blank\">this book {{#i18n \"linkout\"}}{{/i18n}}</a> for more information on setting up a special needs financial planning.  Some states enable parents and caregivers to set up an ABLE account. Learn more about ABLE accounts <a href=\"http://www.realeconomicimpact.org/news/?id=460\" target=\"_blank\">here {{#i18n \"linkout\"}}{{/i18n}}</a> and check out <a href=\"http://cdn2.hubspot.net/hubfs/29051/Comparison_of_State_ABLE_Accounts.pdf?t=1483721245100\" target=\"_blank\">this website {{#i18n \"linkout\"}}{{/i18n}}</a> for a comparison of state ABLE accounts. Also, <a href=\"http://www.margolis.com/our-blog/able-accounts-a-little-deflating\" target=\"_blank\">here {{#i18n \"linkout\"}}{{/i18n}}</a> you can see a chart comparing ABLE accounts and third-party special needs trusts and also read up on some pros and cons of ABLE accounts. <span class=\"why\">{{#i18n \"why\"}}{{/i18n}}</span></p>\n" +
				"                    <div class=\"well\">You indicated that you would like more information on setting up a special needs trust or an ABLE account for {{pcori_dsp_interventi.first_name}}.</div>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.further_info.value \"==\" \"0\"}}\n" +
				"    {{#compare pcori_dsp_interventi.ds_dx1.value \"==\" \"1\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td>\n" +
				"                    <p><strong>Genetic Diagnosis.</strong> You indicated that {{pcori_dsp_interventi.first_name}} has trisomy 21, but you would like more information about the genetics. <a href=\"http://www.massgeneral.org/children/print/down_syndrome_trisomy_21.pdf\" target=\"_blank\">Here is a resource for more information. {{#i18n \"linkout\"}}{{/i18n}}</a> <span class=\"why\">{{#i18n \"why\"}}{{/i18n}}</span></p>\n" +
				"                    <div class=\"well\">You indicated that you would like more information on the genetic diagnosis that has resulted in {{pcori_dsp_interventi.first_name}} having Down syndrome.</div>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.further_info.value \"==\" \"0\"}}\n" +
				"    {{#compare pcori_dsp_interventi.ds_dx2.value \"==\" \"1\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td>\n" +
				"                    <p><strong>Genetic Diagnosis.</strong> You indicated that {{pcori_dsp_interventi.first_name}} has mosaic Down syndrome, but you would like more information about the genetics. <a href=\"http://www.massgeneral.org/children/print/down_syndrome_mosaic.pdf\" target=\"_blank\">Here is a resource. {{#i18n \"linkout\"}}{{/i18n}}</a> <a href=\"http://www.imdsa.org/\" target=\"_blank\">For more information, visit International Mosaic Down Syndrome Association. {{#i18n \"linkout\"}}{{/i18n}}</a> <span class=\"why\">{{#i18n \"why\"}}{{/i18n}}</span></p>\n" +
				"                    <div class=\"well\">You indicated that you would like more information on the genetic diagnosis that has resulted in {{pcori_dsp_interventi.first_name}} having Down syndrome.</div>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.further_info.value \"==\" \"0\"}}\n" +
				"    {{#compare pcori_dsp_interventi.ds_dx3.value \"==\" \"1\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td>\n" +
				"                    <p><strong>Genetic Diagnosis.</strong> You indicated that {{pcori_dsp_interventi.first_name}} has translocation Down syndrome, but you would like more information about the genetics. <a href=\"http://www.massgeneral.org/children/print/down_syndrome_translocation.pdf\" target=\"_blank\">Here is a resource for more information. {{#i18n \"linkout\"}}{{/i18n}}</a> <span class=\"why\">{{#i18n \"why\"}}{{/i18n}}</span></p>\n" +
				"                    <div class=\"well\">You indicated that you would like more information on the genetic diagnosis that has resulted in {{pcori_dsp_interventi.first_name}} having Down syndrome.</div>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"    {{/compare}}\n" +
				"\n" +
				"\n" +
				"\n" +
				"\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.xxxxx.value \"==\" \"xxxxx\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td>\n" +
				"                    <p><strong>xxxxx</strong> <a href=\"xxxxx\" target=\"_blank\">More information on their webpage. {{#i18n \"linkout\"}}{{/i18n}}</a> <a href=\"xxxxx\" target=\"_blank\">Find locations near you. {{#i18n \"linkout\"}}{{/i18n}}</a> <span class=\"why\">{{#i18n \"why\"}}{{/i18n}}</span></p>\n" +
				"                    <div class=\"well\">xxxxx</div>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"\n" +
				"\n" +
				"<!-- end caregiver resources -->\n" +
				"\n" +
				"\n" +
				"</div><!-- .caregiver-wrapper -->\n" +
				"{{/compare}}\n" +
				"\n" +
				"<!-- End caregiver plan -->\n" +
				"\n" +
				"\n" +
				"\n" +
				"<!-- PCP SECTION -->\n" +
				"<!-- PCP SECTION -->\n" +
				"<!-- PCP SECTION -->\n" +
				"\n" +
				"{{#compare sprout.custom.mode \"==\" \"pcp\"}}\n" +
				"<div class=\"pcp-wrapper\">\n" +
				"\n" +
				"    <table width=\"90%\" style=\"margin-right:30px; margin-left:30px;\">\n" +
				"        <tr>\n" +
				"            <td align=\"left\"><img src=\"https://sprouttest.partners.org/sproutassets/brands/pcori-dsp/images/mgh-logo.png\" alt=\"Massachusetts General Hospital Logo\" width=\"252\" height=\"52\"></img></td>\n" +
				"            <td align=\"right\"><img src=\"https://sprouttest.partners.org/sproutassets/brands/pcori-dsp/images/harvard-medical-school-logo.png\" alt=\"Harvard Medical School Logo\" width=\"229\" height=\"52\"></img></td>\n" +
				"        </tr>\n" +
				"    </table>\n" +
				"    <br></br>\n" +
				"\n" +
				"    <p>{{formatDate sprout.submissionDate \"short\"}}</p>\n" +
				"\n" +
				"    <p>Dear {{pcori_dsp_interventi.pcp_name_title.name}} {{pcori_dsp_interventi.pcp_name}},</p>\n" +
				"\n" +
				"    <p>The caregiver of your patient, <strong>{{pcori_dsp_interventi.first_name}} {{pcori_dsp_interventi.last_name}} (DOB {{pcori_dsp_interventi.pt_dob}})</strong>, is participating in a research project aimed at improving healthcare outcomes for patients with Down syndrome.  The project is coordinated by a team of clinicians and researchers at Massachusetts General Hospital in Boston and is funded by a grant from the Patient-Centered Outcomes Research Institute.  More information about our project can be found at <a href=\"http://www.downsyndromeclinictoyou.com\" target=\"_blank\">www.downsyndromeclinictoyou.com</a>.</p>\n" +
				"\n" +
				"    <p>The caregiver of your patient completed an online questionnaire which generated automated suggestions for testing and treatment that are anchored on published practice guidelines and/or expert consensus.  The online tool encouraged <strong>{{pcori_dsp_interventi.first_name}}</strong>'s caregiver to share and discuss these with you.</p>\n" +
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
				"    <h1>Personalized Checklist for {{pcori_dsp_interventi.first_name}}'s Provider</h1>\n" +
				"\n" +
				"    <p>The caregiver of your patient completed an online questionnaire which generated automated suggestions for testing and treatment that are anchored on published practice guidelines and/or expert consensus.</p>\n" +
				"\n" +
				"    <p>This checklist does not establish a health care provider-patient relationship. It is not an attempt to practice medicine or provide specific clinical advice. It is intended to provide useful information to you for reference and educational purposes only. It contains auto-programmed suggestions based on answers provided by your patient's caregiver to the Down Syndrome Clinic to You (DSC2U) survey and on national guidelines. It was not prepared or reviewed by a clinician specifically for your patient. The content of this letter is not meant to be complete or exhaustive or to be a substitute for professional medical advice, diagnosis or treatment. You are responsible for exercising independent judgment about the contents of this letter and for recommending and implementing any care or other course of action for your patient.</p>\n" +
				"\n" +
				"    <br/>\n" +
				"     \n" +
				"    <p>  <img src=\"https://sprouttest.partners.org/sproutassets/brands/pcori-dsp/images/checkmark.png\" class=\"img-checkmark\"></img> You might choose to check each item when completed for {{pcori_dsp_interventi.first_name}}.</p>\n" +
				"\n" +
				"    <!-- End pcp intro -->\n" +
				"\n" +
				"    <!-- PCP URGENT -->\n" +
				"    <!-- PCP URGENT -->\n" +
				"    <!-- PCP URGENT\n" +
				"\n" +
				"    <hr></hr> \n" +
				"    <h2><font color=\"red\">Urgent Suggestions for {{pcori_dsp_interventi.first_name}}</font></h2>\n" +
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
				"    <h2>Recommended Labs, Tests, and Procedures for {{pcori_dsp_interventi.first_name}}</h2>\n" +
				"    <br></br>\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.audio_12m.value \"==\" \"0\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td><p><strong>Audiogram/hearing test.</strong> For ages 1 to 21, AAP recommends annual audiograms given increased incidence of hearing loss in this population.</p></td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.audio_2y.value \"==\" \"0\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td><p><strong>Audiogram/hearing test.</strong> For ages 21 and over, experts recommend an audiogram every two years, given increased incidence of hearing loss in this population.</p></td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.ophtho_1y.value \"==\" \"0\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td><p><strong>Ophthalmology Evaluation:</strong> AAP recommends annual ophthalmologic exams for people with Down syndrome, ages 1 to 5.</p></td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.ophtho_2y.value \"==\" \"0\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td><p><strong>Ophthalmology Evaluation:</strong> AAP recommends an ophthalmologic exam at least every two years for people with Down syndrome, ages 5 to 13.</p></td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.ophtho_3y.value \"==\" \"0\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td><p><strong>Ophthalmology Evaluation:</strong> AAP recommends an ophthalmologic exam at least every three years for people with Down syndrome, ages 13 and 21.</p></td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.ophtho_2yadult.value \"==\" \"0\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td><p><strong>Ophthalmology Evaluation:</strong> Experts recommend an ophthalmologic exam at least every two years for adults with Down syndrome, ages 21 and older.</p></td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.audio_12m.value \"==\" \"999\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td><p><strong>Audiogram/hearing test.</strong> For ages 1 to 21, AAP recommends annual audiograms given increased incidence of hearing loss in this population. {{pcori_dsp_interventi.first_name}}'s caregiver is unsure whether or not {{pcori_dsp_interventi.first_name}} has had a hearing test within the past 12 months.</p></td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.audio_2y.value \"==\" \"999\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td><p><strong>Audiogram/hearing test.</strong> For ages 21 and over, experts recommend  an audiogram every two years,  given increased incidence of hearing loss in this population. {{pcori_dsp_interventi.first_name}}'s caregiver is unsure whether or not {{pcori_dsp_interventi.first_name}} has had a hearing test within the past 2 years.</p></td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.ophtho_1y.value \"==\" \"999\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td><p><strong>Ophthalmology Evaluation:</strong> AAP recommends annual ophthalmologic exams for people with Down syndrome, ages 1 to 5. {{pcori_dsp_interventi.first_name}}'s caregiver indicated that they are unsure whether or not {{pcori_dsp_interventi.first_name}} has had an eye exam within the past year of completing our intake.</p></td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.ophtho_2y.value \"==\" \"999\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td><p><strong>Ophthalmology Evaluation:</strong> AAP recommends an ophthalmologic exam at least every two years for people with Down syndrome, ages 5 to 13. {{pcori_dsp_interventi.first_name}}'s caregiver indicated that they are unsure whether or not {{pcori_dsp_interventi.first_name}} has had an eye exam within the past two years of completing our intake.</p></td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.ophtho_3y.value \"==\" \"999\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td><p><strong>Ophthalmology Evaluation:</strong> AAP recommends an ophthalmologic exam at least every three years for people with Down syndrome, ages 13 and 21. {{pcori_dsp_interventi.first_name}}'s caregiver indicated that they are unsure whether or not {{pcori_dsp_interventi.first_name}} has had an eye exam within the past three years of completing our intake.</p></td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.ophtho_2yadult.value \"==\" \"999\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td><p><strong>Ophthalmology Evaluation:</strong> Experts recommend an ophthalmologic exam at least every two years for adults with Down syndrome, ages 21 and older. {{pcori_dsp_interventi.first_name}}'s caregiver indicated that they are unsure whether or not {{pcori_dsp_interventi.first_name}} has had an eye exam within the past two years of completing our intake.</p></td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.celiac_test.value \"==\" \"0\"}}\n" +
				"    {{#if (or\n" +
				"        (eq pcori_dsp_interventi.hidden_celiac1 \"1\")\n" +
				"        (eq pcori_dsp_interventi.hidden_celiac2 \"1\")\n" +
				"    )}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td><p><strong>Celiac screen</strong> (total IgA &amp; TTG-IgA)<strong>.</strong> {{pcori_dsp_interventi.first_name}}’s caregiver indicated that within the past month of completing our intake, {{pcori_dsp_interventi.heshe}} had\n" +
				"                            {{#compare pcori_dsp_interventi.constipation.value \"==\" \"1\"}}constipation{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.diarrhea.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.nausea.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.vomiting.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.stool.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.incontinence_stool.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.flatulence.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bloating.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.ftt.value \"1\")\n" +
				"                            )}}, {{else}}{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.hyperactivity_adhd.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.inattention.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.impulsivity.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.distracted.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.uncooperative.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.temper_meltdowns_irritability.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.diarrhea.value \"==\" \"1\"}}diarrhea{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.nausea.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.vomiting.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.stool.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.incontinence_stool.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.flatulence.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bloating.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.ftt.value \"1\")\n" +
				"                            )}}, {{else}}{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.hyperactivity_adhd.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.inattention.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.impulsivity.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.distracted.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.uncooperative.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.temper_meltdowns_irritability.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.nausea.value \"==\" \"1\"}}nausea{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.vomiting.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.stool.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.incontinence_stool.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.flatulence.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bloating.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.ftt.value \"1\")\n" +
				"                            )}}, {{else}}{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.hyperactivity_adhd.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.inattention.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.impulsivity.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.distracted.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.uncooperative.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.temper_meltdowns_irritability.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.vomiting.value \"==\" \"1\"}}vomiting{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.stool.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.incontinence_stool.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.flatulence.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bloating.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.ftt.value \"1\")\n" +
				"                            )}}, {{else}}{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.hyperactivity_adhd.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.inattention.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.impulsivity.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.distracted.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.uncooperative.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.temper_meltdowns_irritability.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.stool.value \"==\" \"1\"}}bulky or foul-smelling stools{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.incontinence_stool.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.flatulence.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bloating.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.ftt.value \"1\")\n" +
				"                            )}}, {{else}}{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.hyperactivity_adhd.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.inattention.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.impulsivity.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.distracted.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.uncooperative.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.temper_meltdowns_irritability.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.incontinence_stool.value \"==\" \"1\"}}new accidents with stool{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.flatulence.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bloating.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.ftt.value \"1\")\n" +
				"                            )}}, {{else}}{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.hyperactivity_adhd.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.inattention.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.impulsivity.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.distracted.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.uncooperative.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.temper_meltdowns_irritability.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.flatulence.value \"==\" \"1\"}}passing excessive gas{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.bloating.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.ftt.value \"1\")\n" +
				"                            )}}, {{else}}{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.hyperactivity_adhd.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.inattention.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.impulsivity.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.distracted.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.uncooperative.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.temper_meltdowns_irritability.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.bloating.value \"==\" \"1\"}}bloating{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.ftt.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hyperactivity_adhd.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.inattention.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.impulsivity.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.distracted.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.uncooperative.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.temper_meltdowns_irritability.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.ftt.value \"==\" \"1\"}}unexplained growth failure{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.hyperactivity_adhd.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.inattention.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.impulsivity.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.distracted.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.uncooperative.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.temper_meltdowns_irritability.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.hyperactivity_adhd.value \"==\" \"1\"}}overactive behavior, restlessness, inability to sit still{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.inattention.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.impulsivity.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.distracted.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.uncooperative.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.temper_meltdowns_irritability.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.inattention.value \"==\" \"1\"}}inattention{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.impulsivity.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.distracted.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.uncooperative.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.temper_meltdowns_irritability.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.impulsivity.value \"==\" \"1\"}}impulsivity, acting without thinking{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.distracted.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.uncooperative.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.temper_meltdowns_irritability.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.distracted.value \"==\" \"1\"}}easily distracted{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.uncooperative.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.temper_meltdowns_irritability.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.uncooperative.value \"==\" \"1\"}}uncooperative or disobedient behavior{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.temper_meltdowns_irritability.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.temper_meltdowns_irritability.value \"==\" \"1\"}}temper tantrums or outbursts or meltdowns{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.hits_self.value \"==\" \"1\"}}hitting self{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.bites_self.value \"==\" \"1\"}}biting self{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.injures_self.value \"==\" \"1\"}}hurting self on purpose{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.bangs_head.value \"==\" \"1\"}}banging head{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.irritable.value \"==\" \"1\"}}irritability{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.hits_others.value \"==\" \"1\"}}hitting or kicking others{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.mood_swings.value \"==\" \"1\"}}mood swings{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.aggression.value \"==\" \"1\"}}aggression{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.property_destruction.value \"==\" \"1\"}}property destruction{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.cries_easily.value \"==\" \"1\"}}crying easily or for no reason{{#compare pcori_dsp_interventi.lose_skills.value \"==\" \"1\"}}, {{else}}.{{/compare}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.lose_skills.value \"==\" \"1\"}}loss of previously learned skills.{{/compare}}\n" +
				"                {{pcori_dsp_interventi.first_name}} has never had celiac testing done. Celiac disease is more common in people with Down syndrome.</p></td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/if}}\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.celiac_test.value \"==\" \"999\"}}\n" +
				"    {{#if (or\n" +
				"        (eq pcori_dsp_interventi.hidden_celiac1 \"1\")\n" +
				"        (eq pcori_dsp_interventi.hidden_celiac2 \"1\")\n" +
				"    )}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td><p><strong>Celiac screen</strong> (total IgA &amp; TTG-IgA)<strong>.</strong> {{pcori_dsp_interventi.first_name}}’s caregiver indicated that within the past month of completing our intake, {{pcori_dsp_interventi.heshe}} had\n" +
				"                            {{#compare pcori_dsp_interventi.constipation.value \"==\" \"1\"}}constipation{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.diarrhea.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.nausea.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.vomiting.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.stool.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.incontinence_stool.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.flatulence.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bloating.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.ftt.value \"1\")\n" +
				"                            )}}, {{else}}{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.hyperactivity_adhd.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.inattention.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.impulsivity.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.distracted.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.uncooperative.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.temper_meltdowns_irritability.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.diarrhea.value \"==\" \"1\"}}diarrhea{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.nausea.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.vomiting.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.stool.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.incontinence_stool.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.flatulence.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bloating.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.ftt.value \"1\")\n" +
				"                            )}}, {{else}}{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.hyperactivity_adhd.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.inattention.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.impulsivity.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.distracted.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.uncooperative.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.temper_meltdowns_irritability.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.nausea.value \"==\" \"1\"}}nausea{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.vomiting.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.stool.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.incontinence_stool.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.flatulence.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bloating.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.ftt.value \"1\")\n" +
				"                            )}}, {{else}}{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.hyperactivity_adhd.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.inattention.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.impulsivity.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.distracted.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.uncooperative.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.temper_meltdowns_irritability.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.vomiting.value \"==\" \"1\"}}vomiting{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.stool.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.incontinence_stool.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.flatulence.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bloating.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.ftt.value \"1\")\n" +
				"                            )}}, {{else}}{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.hyperactivity_adhd.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.inattention.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.impulsivity.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.distracted.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.uncooperative.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.temper_meltdowns_irritability.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.stool.value \"==\" \"1\"}}bulky or foul-smelling stools{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.incontinence_stool.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.flatulence.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bloating.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.ftt.value \"1\")\n" +
				"                            )}}, {{else}}{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.hyperactivity_adhd.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.inattention.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.impulsivity.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.distracted.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.uncooperative.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.temper_meltdowns_irritability.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.incontinence_stool.value \"==\" \"1\"}}new accidents with stool{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.flatulence.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bloating.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.ftt.value \"1\")\n" +
				"                            )}}, {{else}}{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.hyperactivity_adhd.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.inattention.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.impulsivity.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.distracted.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.uncooperative.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.temper_meltdowns_irritability.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.flatulence.value \"==\" \"1\"}}passing excessive gas{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.bloating.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.ftt.value \"1\")\n" +
				"                            )}}, {{else}}{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.hyperactivity_adhd.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.inattention.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.impulsivity.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.distracted.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.uncooperative.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.temper_meltdowns_irritability.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.bloating.value \"==\" \"1\"}}bloating{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.ftt.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hyperactivity_adhd.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.inattention.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.impulsivity.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.distracted.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.uncooperative.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.temper_meltdowns_irritability.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.ftt.value \"==\" \"1\"}}unexplained growth failure{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.hyperactivity_adhd.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.inattention.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.impulsivity.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.distracted.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.uncooperative.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.temper_meltdowns_irritability.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.hyperactivity_adhd.value \"==\" \"1\"}}overactive behavior, restlessness, inability to sit still{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.inattention.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.impulsivity.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.distracted.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.uncooperative.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.temper_meltdowns_irritability.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.inattention.value \"==\" \"1\"}}inattention{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.impulsivity.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.distracted.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.uncooperative.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.temper_meltdowns_irritability.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.impulsivity.value \"==\" \"1\"}}impulsivity, acting without thinking{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.distracted.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.uncooperative.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.temper_meltdowns_irritability.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.distracted.value \"==\" \"1\"}}easily distracted{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.uncooperative.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.temper_meltdowns_irritability.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.uncooperative.value \"==\" \"1\"}}uncooperative or disobedient behavior{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.temper_meltdowns_irritability.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.temper_meltdowns_irritability.value \"==\" \"1\"}}temper tantrums or outbursts or meltdowns{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.hits_self.value \"==\" \"1\"}}hitting self{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.bites_self.value \"==\" \"1\"}}biting self{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.injures_self.value \"==\" \"1\"}}hurting self on purpose{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.bangs_head.value \"==\" \"1\"}}banging head{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.irritable.value \"==\" \"1\"}}irritability{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.hits_others.value \"==\" \"1\"}}hitting or kicking others{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.mood_swings.value \"==\" \"1\"}}mood swings{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.aggression.value \"==\" \"1\"}}aggression{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.property_destruction.value \"==\" \"1\"}}property destruction{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.cries_easily.value \"==\" \"1\"}}crying easily or for no reason{{#compare pcori_dsp_interventi.lose_skills.value \"==\" \"1\"}}, {{else}}.{{/compare}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.lose_skills.value \"==\" \"1\"}}loss of previously learned skills.{{/compare}}\n" +
				"                {{pcori_dsp_interventi.first_name}}’s caregiver is also not sure whether or not {{pcori_dsp_interventi.first_name}} has ever had celiac testing done. Celiac disease is more common in people with Down syndrome. You should consider testing if celiac testing has never been done, or if it has not been done within the past 12 months.</p></td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/if}}\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.celiac_test.value \"==\" \"1\"}}\n" +
				"    {{#compare pcori_dsp_interventi.celiac_time.value \"==\" \"0\"}}\n" +
				"    {{#compare pcori_dsp_interventi.celiacd.value \"!=\" \"1\"}}\n" +
				"    {{#if (or\n" +
				"        (eq pcori_dsp_interventi.hidden_celiac1 \"1\")\n" +
				"        (eq pcori_dsp_interventi.hidden_celiac2 \"1\")\n" +
				"    )}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td><p><strong>Celiac screen</strong> (total IgA &amp; TTG-IgA)<strong>.</strong> {{pcori_dsp_interventi.first_name}}’s caregiver indicated that within the past month of completing our intake, {{pcori_dsp_interventi.heshe}} had\n" +
				"                            {{#compare pcori_dsp_interventi.constipation.value \"==\" \"1\"}}constipation{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.diarrhea.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.nausea.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.vomiting.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.stool.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.incontinence_stool.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.flatulence.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bloating.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.ftt.value \"1\")\n" +
				"                            )}}, {{else}}{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.hyperactivity_adhd.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.inattention.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.impulsivity.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.distracted.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.uncooperative.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.temper_meltdowns_irritability.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.diarrhea.value \"==\" \"1\"}}diarrhea{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.nausea.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.vomiting.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.stool.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.incontinence_stool.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.flatulence.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bloating.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.ftt.value \"1\")\n" +
				"                            )}}, {{else}}{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.hyperactivity_adhd.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.inattention.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.impulsivity.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.distracted.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.uncooperative.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.temper_meltdowns_irritability.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.nausea.value \"==\" \"1\"}}nausea{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.vomiting.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.stool.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.incontinence_stool.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.flatulence.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bloating.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.ftt.value \"1\")\n" +
				"                            )}}, {{else}}{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.hyperactivity_adhd.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.inattention.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.impulsivity.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.distracted.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.uncooperative.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.temper_meltdowns_irritability.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.vomiting.value \"==\" \"1\"}}vomiting{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.stool.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.incontinence_stool.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.flatulence.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bloating.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.ftt.value \"1\")\n" +
				"                            )}}, {{else}}{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.hyperactivity_adhd.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.inattention.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.impulsivity.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.distracted.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.uncooperative.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.temper_meltdowns_irritability.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.stool.value \"==\" \"1\"}}bulky or foul-smelling stools{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.incontinence_stool.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.flatulence.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bloating.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.ftt.value \"1\")\n" +
				"                            )}}, {{else}}{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.hyperactivity_adhd.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.inattention.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.impulsivity.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.distracted.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.uncooperative.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.temper_meltdowns_irritability.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.incontinence_stool.value \"==\" \"1\"}}new accidents with stool{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.flatulence.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bloating.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.ftt.value \"1\")\n" +
				"                            )}}, {{else}}{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.hyperactivity_adhd.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.inattention.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.impulsivity.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.distracted.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.uncooperative.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.temper_meltdowns_irritability.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.flatulence.value \"==\" \"1\"}}passing excessive gas{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.bloating.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.ftt.value \"1\")\n" +
				"                            )}}, {{else}}{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.hyperactivity_adhd.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.inattention.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.impulsivity.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.distracted.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.uncooperative.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.temper_meltdowns_irritability.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.bloating.value \"==\" \"1\"}}bloating{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.ftt.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hyperactivity_adhd.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.inattention.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.impulsivity.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.distracted.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.uncooperative.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.temper_meltdowns_irritability.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.ftt.value \"==\" \"1\"}}unexplained growth failure{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.hyperactivity_adhd.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.inattention.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.impulsivity.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.distracted.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.uncooperative.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.temper_meltdowns_irritability.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.hyperactivity_adhd.value \"==\" \"1\"}}overactive behavior, restlessness, inability to sit still{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.inattention.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.impulsivity.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.distracted.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.uncooperative.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.temper_meltdowns_irritability.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.inattention.value \"==\" \"1\"}}inattention{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.impulsivity.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.distracted.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.uncooperative.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.temper_meltdowns_irritability.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.impulsivity.value \"==\" \"1\"}}impulsivity, acting without thinking{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.distracted.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.uncooperative.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.temper_meltdowns_irritability.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.distracted.value \"==\" \"1\"}}easily distracted{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.uncooperative.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.temper_meltdowns_irritability.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.uncooperative.value \"==\" \"1\"}}uncooperative or disobedient behavior{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.temper_meltdowns_irritability.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.temper_meltdowns_irritability.value \"==\" \"1\"}}temper tantrums or outbursts or meltdowns{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.hits_self.value \"==\" \"1\"}}hitting self{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.bites_self.value \"==\" \"1\"}}biting self{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.injures_self.value \"==\" \"1\"}}hurting self on purpose{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.bangs_head.value \"==\" \"1\"}}banging head{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.irritable.value \"==\" \"1\"}}irritability{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.hits_others.value \"==\" \"1\"}}hitting or kicking others{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.mood_swings.value \"==\" \"1\"}}mood swings{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.aggression.value \"==\" \"1\"}}aggression{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.property_destruction.value \"==\" \"1\"}}property destruction{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.cries_easily.value \"==\" \"1\"}}crying easily or for no reason{{#compare pcori_dsp_interventi.lose_skills.value \"==\" \"1\"}}, {{else}}.{{/compare}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.lose_skills.value \"==\" \"1\"}}loss of previously learned skills.{{/compare}}\n" +
				"                {{pcori_dsp_interventi.first_name}}’s caregiver indicated that celiac testing has not been done within the past 12 months.</p></td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/if}}\n" +
				"    {{/compare}}\n" +
				"    {{/compare}}\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.celiac_test.value \"==\" \"1\"}}\n" +
				"    {{#compare pcori_dsp_interventi.celiac_time.value \"==\" \"0\"}}\n" +
				"    {{#compare pcori_dsp_interventi.celiacd.value \"!=\" \"1\"}}\n" +
				"    {{#compare pcori_dsp_interventi.gluten_free.value \"!=\" \"1\"}}\n" +
				"    {{#if (or\n" +
				"        (eq pcori_dsp_interventi.hidden_celiac1 \"1\")\n" +
				"        (eq pcori_dsp_interventi.hidden_celiac2 \"1\")\n" +
				"    )}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td><p><strong>Celiac screen</strong> (total IgA &amp; TTG-IgA)<strong>.</strong> {{pcori_dsp_interventi.first_name}}’s caregiver indicated that within the past month of completing our intake, {{pcori_dsp_interventi.heshe}} had\n" +
				"                            {{#compare pcori_dsp_interventi.constipation.value \"==\" \"1\"}}constipation{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.diarrhea.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.nausea.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.vomiting.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.stool.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.incontinence_stool.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.flatulence.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bloating.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.ftt.value \"1\")\n" +
				"                            )}}, {{else}}{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.hyperactivity_adhd.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.inattention.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.impulsivity.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.distracted.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.uncooperative.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.temper_meltdowns_irritability.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.diarrhea.value \"==\" \"1\"}}diarrhea{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.nausea.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.vomiting.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.stool.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.incontinence_stool.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.flatulence.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bloating.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.ftt.value \"1\")\n" +
				"                            )}}, {{else}}{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.hyperactivity_adhd.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.inattention.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.impulsivity.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.distracted.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.uncooperative.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.temper_meltdowns_irritability.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.nausea.value \"==\" \"1\"}}nausea{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.vomiting.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.stool.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.incontinence_stool.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.flatulence.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bloating.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.ftt.value \"1\")\n" +
				"                            )}}, {{else}}{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.hyperactivity_adhd.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.inattention.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.impulsivity.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.distracted.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.uncooperative.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.temper_meltdowns_irritability.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.vomiting.value \"==\" \"1\"}}vomiting{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.stool.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.incontinence_stool.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.flatulence.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bloating.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.ftt.value \"1\")\n" +
				"                            )}}, {{else}}{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.hyperactivity_adhd.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.inattention.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.impulsivity.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.distracted.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.uncooperative.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.temper_meltdowns_irritability.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.stool.value \"==\" \"1\"}}bulky or foul-smelling stools{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.incontinence_stool.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.flatulence.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bloating.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.ftt.value \"1\")\n" +
				"                            )}}, {{else}}{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.hyperactivity_adhd.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.inattention.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.impulsivity.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.distracted.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.uncooperative.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.temper_meltdowns_irritability.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.incontinence_stool.value \"==\" \"1\"}}new accidents with stool{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.flatulence.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bloating.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.ftt.value \"1\")\n" +
				"                            )}}, {{else}}{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.hyperactivity_adhd.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.inattention.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.impulsivity.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.distracted.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.uncooperative.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.temper_meltdowns_irritability.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.flatulence.value \"==\" \"1\"}}passing excessive gas{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.bloating.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.ftt.value \"1\")\n" +
				"                            )}}, {{else}}{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.hyperactivity_adhd.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.inattention.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.impulsivity.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.distracted.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.uncooperative.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.temper_meltdowns_irritability.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.bloating.value \"==\" \"1\"}}bloating{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.ftt.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hyperactivity_adhd.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.inattention.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.impulsivity.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.distracted.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.uncooperative.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.temper_meltdowns_irritability.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.ftt.value \"==\" \"1\"}}unexplained growth failure{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.hyperactivity_adhd.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.inattention.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.impulsivity.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.distracted.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.uncooperative.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.temper_meltdowns_irritability.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.hyperactivity_adhd.value \"==\" \"1\"}}overactive behavior, restlessness, inability to sit still{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.inattention.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.impulsivity.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.distracted.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.uncooperative.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.temper_meltdowns_irritability.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.inattention.value \"==\" \"1\"}}inattention{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.impulsivity.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.distracted.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.uncooperative.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.temper_meltdowns_irritability.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.impulsivity.value \"==\" \"1\"}}impulsivity, acting without thinking{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.distracted.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.uncooperative.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.temper_meltdowns_irritability.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.distracted.value \"==\" \"1\"}}easily distracted{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.uncooperative.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.temper_meltdowns_irritability.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.uncooperative.value \"==\" \"1\"}}uncooperative or disobedient behavior{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.temper_meltdowns_irritability.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.temper_meltdowns_irritability.value \"==\" \"1\"}}temper tantrums or outbursts or meltdowns{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.hits_self.value \"==\" \"1\"}}hitting self{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.bites_self.value \"==\" \"1\"}}biting self{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.injures_self.value \"==\" \"1\"}}hurting self on purpose{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.bangs_head.value \"==\" \"1\"}}banging head{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.irritable.value \"==\" \"1\"}}irritability{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.hits_others.value \"==\" \"1\"}}hitting or kicking others{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.mood_swings.value \"==\" \"1\"}}mood swings{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.aggression.value \"==\" \"1\"}}aggression{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.property_destruction.value \"==\" \"1\"}}property destruction{{#if (or\n" +
				"                                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
				"                                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
				"                            )}}, {{else}}.{{/if}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.cries_easily.value \"==\" \"1\"}}crying easily or for no reason{{#compare pcori_dsp_interventi.lose_skills.value \"==\" \"1\"}}, {{else}}.{{/compare}}{{/compare}}\n" +
				"                        {{#compare pcori_dsp_interventi.lose_skills.value \"==\" \"1\"}}loss of previously learned skills.{{/compare}}\n" +
				"                {{pcori_dsp_interventi.heshe}} already has a diagnosis of celiac disease and is on a gluten-free diet, but since {{pcori_dsp_interventi.heshe}} has not had celiac testing done within the past 12 months, repeat testing to assess for any residual gluten in {{pcori_dsp_interventi.hishertheir}} diet might be helpful.</p></td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/if}}\n" +
				"    {{/compare}}\n" +
				"    {{/compare}}\n" +
				"    {{/compare}}\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    <!-- insert sleep apnea labs -->\n" +
				"\n" +
				"    <!-- insert thyroid labs -->\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.pt_age \"<\" \"21\"}}\n" +
				"    {{#compare pcori_dsp_interventi.pmh_anemia.value \"!=\" \"1\"}}\n" +
				"    {{#compare pcori_dsp_interventi.hgb.value \"==\" \"0\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td>\n" +
				"                    <p><strong>Hemoglobin level.</strong> {{pcori_dsp_interventi.first_name}}’s caregiver indicated that within the past 12 months of completing our intake, {{pcori_dsp_interventi.first_name}} has not had a hemoglobin level.  The AAP recommends that everyone with Down syndrome have a hemoglobin level checked annually to assess for iron-deficiency anemia.  If Hg < 11, the AAP recommends one of these follow-up labs: (1) ferritin and CRP or (2) reticulocyte hemoglobin (CHr).  If (1) CRP is normal and ferritin is low or (2) CHr is low, then iron augmentation through diet or supplements is recommended.</p>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"    {{/compare}}\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.pt_age \"<\" \"21\"}}\n" +
				"    {{#compare pcori_dsp_interventi.pmh_anemia.value \"==\" \"1\"}}\n" +
				"    {{#compare pcori_dsp_interventi.hgb.value \"==\" \"0\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td>\n" +
				"                    <p><strong>Hemoglobin level.</strong> {{pcori_dsp_interventi.first_name}}’s caregiver indicated that within the past 12 months of completing our intake, {{pcori_dsp_interventi.first_name}} has not had a hemoglobin level, and they already have a diagnosis of anemia. The AAP recommends that everyone with Down syndrome have a hemoglobin level checked annually to monitor iron-deficiency anemia and adjust treatment options.</p>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"    {{/compare}}\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.pt_age \"<\" \"21\"}}\n" +
				"    {{#compare pcori_dsp_interventi.pmh_anemia.value \"!=\" \"1\"}}\n" +
				"    {{#compare pcori_dsp_interventi.hgb.value \"==\" \"999\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td>\n" +
				"                    <p><strong>Hemoglobin level.</strong> {{pcori_dsp_interventi.first_name}}’s caregiver indicated that they are not sure if {{pcori_dsp_interventi.first_name}} has had a hemoglobin level performed within the past 12 months. The AAP recommends that everyone with Down syndrome have a hemoglobin level checked annually to assess for iron-deficiency anemia.  If Hg < 11, the AAP recommends one of these follow-up labs: (1) ferritin and CRP or (2) reticulocyte hemoglobin (CHr).  If (1) CRP is normal and ferritin is low or (2) CHr is low, then iron augmentation through diet or supplements is recommended.</p>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"    {{/compare}}\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.pt_age \"<\" \"21\"}}\n" +
				"    {{#compare pcori_dsp_interventi.pmh_anemia.value \"==\" \"1\"}}\n" +
				"    {{#compare pcori_dsp_interventi.hgb.value \"==\" \"999\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td>\n" +
				"                    <p><strong>Hemoglobin level.</strong> {{pcori_dsp_interventi.first_name}}’s caregiver indicated that within the past 12 months of completing our intake, they were not sure if {{pcori_dsp_interventi.first_name}} has had a hemoglobin level, and they already have a diagnosis of anemia. The AAP recommends that everyone with Down syndrome have a hemoglobin level checked annually to monitor iron-deficiency anemia and adjust treatment options. </p>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"    {{/compare}}\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    <!-- insert vfss labs -->\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.dentist.value \"==\" \"0\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td>\n" +
				"                    <p><strong>Dental visit.</strong> {{pcori_dsp_interventi.first_name}}’s caregiver indicated that {{pcori_dsp_interventi.first_name}} has not had a dental exam within the past 6 months.</p>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.dentist.value \"==\" \"999\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td>\n" +
				"                    <p><strong>Dental visit.</strong> {{pcori_dsp_interventi.first_name}}’s caregiver indicated that they were unsure if {{pcori_dsp_interventi.first_name}} had a dental exam within the past 6 months.</p>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.colonca_screening.value \"==\" \"0\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td>\n" +
				"                    <p><strong>Colonoscopy.</strong> {{pcori_dsp_interventi.first_name}}’s caregiver indicated that {{pcori_dsp_interventi.first_name}} has never had screening for colon cancer.  Research has demonstrated that colon cancer is less common in adults with Down syndrome.  Colon cancer screening might still be appropriate, however, if there is a strong family history of colon cancer or other risk factors.</p>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.colonca_screening.value \"==\" \"999\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td>\n" +
				"                    <p><strong>Colonoscopy.</strong> {{pcori_dsp_interventi.first_name}}’s caregiver indicated that they are unsure whether or not {{pcori_dsp_interventi.first_name}} has ever had a colon cancer screening. Research has demonstrated that colon cancer is less common in adults with Down syndrome.  Colon cancer screening might still be appropriate, however, if there is a strong family history of colon cancer or other risk factors.</p>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.mammogram.value \"==\" \"0\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td>\n" +
				"                    <p><strong>Mammogram.</strong> {{pcori_dsp_interventi.first_name}}’s caregiver indicated that {{pcori_dsp_interventi.first_name}} has never had a mammogram. Research has demonstrated that breast cancer is less common in adults with Down syndrome. Experts specializing in Down syndrome still recommend that women with Down syndrome beginning at the age of fifty adhere to routine mammogram screening as in the general population (once every two years), particularly if there is a family history of breast cancer or other risk factors.</p>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.mammogram.value \"==\" \"999\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td>\n" +
				"                    <p><strong>Mammogram.</strong> {{pcori_dsp_interventi.first_name}}’s caregiver indicated that they are unsure whether or not {{pcori_dsp_interventi.first_name}} has ever had a mammogram. Experts specializing in Down syndrome still recommend that women with Down syndrome beginning at the age of fifty adhere to routine mammogram screening as in the general population (once every two years), particularly if there is a family history of breast cancer or other risk factors.</p>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.pap_smear.value \"==\" \"0\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td>\n" +
				"                    <p><strong>Pap smear.</strong> {{pcori_dsp_interventi.first_name}}’s caregiver indicated that {{pcori_dsp_interventi.first_name}} has never had a Pap smear. There is a very low risk of developing cervical cancer in the absence of having sexual intercourse. Nonetheless, experts specializing in Down syndrome recommend that adult women with Down syndrome consider having a Pap smear, particularly if they are sexually active. Performing a Pap smear on an adult woman who has not had sexual intercourse can be challenging, in which case a blind Pap smear may be attempted.</p>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.pap_smear.value \"==\" \"999\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">{{#i18n \"checkmark\"}}{{/i18n}}</td>\n" +
				"                <td>\n" +
				"                    <p><strong>Pap smear.</strong> {{pcori_dsp_interventi.first_name}}’s caregiver indicated that they were unsure if {{pcori_dsp_interventi.first_name}} has ever had a Pap smear. There is a very low risk of developing cervical cancer in the absence of having sexual intercourse. Nonetheless, experts specializing in Down syndrome recommend that adult women with Down syndrome consider having a Pap smear, particularly if they are sexually active. Performing a Pap smear on an adult woman who has not had sexual intercourse can be challenging, in which case a blind Pap smear may be attempted.</p>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
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
				"    <h2>OTHER INFORMATION, RESOURCES, and SUPPORTS for {{pcori_dsp_interventi.first_name}}</h2>\n" +
				"    <h3>For your information, we provided {{pcori_dsp_interventi.first_name}}s caregiver links to the following online resources and information.  If you would like online access to these resources, please go to !!!!!URL to be inserted!!!!!.</h3>\n" +
				"\n" +
				"\n" +
				"\n" +
				"    <br></br>\n" +
				"    <h2>Health and Wellness Resources</h2>\n" +
				"    <br></br>\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.audio_12m.value \"==\" \"1\"}}\n" +
				"        {{#compare pcori_dsp_interventi.hearing_loss.value \"==\" \"1\"}}\n" +
				"            <table class=\"table-checklist\">\n" +
				"                <tr>\n" +
				"                    <td class=\"td-checkbox\">&#x25cf;</td>\n" +
				"                    <td><p>Concerns about hearing loss</p></td>\n" +
				"                </tr>\n" +
				"            </table>\n" +
				"        {{/compare}}\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.audio_2y.value \"==\" \"1\"}}\n" +
				"        {{#compare pcori_dsp_interventi.hearing_loss.value \"==\" \"1\"}}\n" +
				"            <table class=\"table-checklist\">\n" +
				"                <tr>\n" +
				"                    <td class=\"td-checkbox\">&#x25cf;</td>\n" +
				"                    <td><p>Concerns about hearing loss</p></td>\n" +
				"                </tr>\n" +
				"            </table>\n" +
				"        {{/compare}}\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#if (or\n" +
				"        (eq pcori_dsp_interventi.ophtho_1y.value \"1\")\n" +
				"        (eq pcori_dsp_interventi.ophtho_2y.value \"1\")\n" +
				"        (eq pcori_dsp_interventi.ophtho_3y.value \"1\")\n" +
				"        (eq pcori_dsp_interventi.ophtho_2yadult.value \"1\")\n" +
				"    )}}\n" +
				"    {{#if (and\n" +
				"        (eq pcori_dsp_interventi.concerns_about_vision.value \"1\")\n" +
				"        (eq pcori_dsp_interventi.keratoconus.value \"\")\n" +
				"        (eq pcori_dsp_interventi.cataracts.value \"\")\n" +
				"        (eq pcori_dsp_interventi.strabismus.value \"\")\n" +
				"        (eq pcori_dsp_interventi.nystagmus.value \"\")\n" +
				"        (eq pcori_dsp_interventi.astigmatism.value \"\")\n" +
				"    )}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">&#x25cf;</td>\n" +
				"                <td><p>Vision concerns in people with Down syndrome</p></td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/if}}\n" +
				"    {{/if}}\n" +
				"\n" +
				"    {{#if (or\n" +
				"        (eq pcori_dsp_interventi.ophtho_1y.value \"1\")\n" +
				"        (eq pcori_dsp_interventi.ophtho_2y.value \"1\")\n" +
				"        (eq pcori_dsp_interventi.ophtho_3y.value \"1\")\n" +
				"        (eq pcori_dsp_interventi.ophtho_2yadult.value \"1\")\n" +
				"    )}}\n" +
				"    {{#if (and\n" +
				"        (eq pcori_dsp_interventi.concerns_about_vision.value \"1\")\n" +
				"        (eq pcori_dsp_interventi.keratoconus.value \"1\")\n" +
				"    )}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">&#x25cf;</td>\n" +
				"                <td><p>Keratoconus in people with Down syndrome</p></td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/if}}\n" +
				"    {{/if}}\n" +
				"\n" +
				"    {{#if (or\n" +
				"        (eq pcori_dsp_interventi.ophtho_1y.value \"1\")\n" +
				"        (eq pcori_dsp_interventi.ophtho_2y.value \"1\")\n" +
				"        (eq pcori_dsp_interventi.ophtho_3y.value \"1\")\n" +
				"        (eq pcori_dsp_interventi.ophtho_2yadult.value \"1\")\n" +
				"    )}}\n" +
				"    {{#if (and\n" +
				"        (eq pcori_dsp_interventi.concerns_about_vision.value \"1\")\n" +
				"        (eq pcori_dsp_interventi.cataracts.value \"1\")\n" +
				"    )}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">&#x25cf;</td>\n" +
				"                <td><p>Cataracts in people with Down syndrome</p></td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/if}}\n" +
				"    {{/if}}\n" +
				"\n" +
				"    {{#if (or\n" +
				"        (eq pcori_dsp_interventi.ophtho_1y.value \"1\")\n" +
				"        (eq pcori_dsp_interventi.ophtho_2y.value \"1\")\n" +
				"        (eq pcori_dsp_interventi.ophtho_3y.value \"1\")\n" +
				"        (eq pcori_dsp_interventi.ophtho_2yadult.value \"1\")\n" +
				"    )}}\n" +
				"    {{#compare pcori_dsp_interventi.involuntary_eye_movements.value \"==\" \"1\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">&#x25cf;</td>\n" +
				"                <td><p>Nystagmus in people with Down syndrome</p></td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"    {{/if}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.celiac_test.value \"==\" \"1\"}}\n" +
				"    {{#compare pcori_dsp_interventi.celiacd.value \"==\" \"1\"}}\n" +
				"    {{#compare pcori_dsp_interventi.gluten_free.value \"==\" \"2\"}}\n" +
				"    {{#if (or\n" +
				"        (eq pcori_dsp_interventi.hidden_celiac1 \"1\")\n" +
				"        (eq pcori_dsp_interventi.hidden_celiac2 \"1\")\n" +
				"    )}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">&#x25cf;</td>\n" +
				"                <td><p>Gluten-free foods</p></td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/if}}\n" +
				"    {{/compare}}\n" +
				"    {{/compare}}\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.celiac_test.value \"==\" \"1\"}}\n" +
				"    {{#compare pcori_dsp_interventi.celiac_time.value \"==\" \"1\"}}\n" +
				"    {{#compare pcori_dsp_interventi.celiacd.value \"==\" \"1\"}}\n" +
				"    {{#compare pcori_dsp_interventi.gluten_free.value \"==\" \"1\"}}\n" +
				"    {{#if (or\n" +
				"        (eq pcori_dsp_interventi.hidden_celiac1 \"1\")\n" +
				"        (eq pcori_dsp_interventi.hidden_celiac2 \"1\")\n" +
				"    )}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">&#x25cf;</td>\n" +
				"                <td><p>Resource on gluten-free foods</p></td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/if}}\n" +
				"    {{/compare}}\n" +
				"    {{/compare}}\n" +
				"    {{/compare}}\n" +
				"    {{/compare}}\n" +
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
				"    {{#if (or\n" +
				"        (eq pcori_dsp_interventi.gen_md_phone.value \"2\")\n" +
				"        (eq pcori_dsp_interventi.gen_md_phone.value \"1\")\n" +
				"        (eq pcori_dsp_interventi.gen_md_talk.value \"2\")\n" +
				"        (eq pcori_dsp_interventi.gen_md_talk.value \"1\")\n" +
				"        (eq pcori_dsp_interventi.gen_sx_describe.value \"2\")\n" +
				"        (eq pcori_dsp_interventi.gen_sx_describe.value \"1\")\n" +
				"        (eq pcori_dsp_interventi.gen_meds.value \"2\")\n" +
				"        (eq pcori_dsp_interventi.gen_meds.value \"1\")\n" +
				"        (eq pcori_dsp_interventi.gen_meds_reason.value \"2\")\n" +
				"        (eq pcori_dsp_interventi.gen_meds_reason.value \"1\")\n" +
				"        (eq pcori_dsp_interventi.gen_meds_take.value \"2\")\n" +
				"        (eq pcori_dsp_interventi.gen_meds_take.value \"1\")\n" +
				"        (eq pcori_dsp_interventi.gen_rx.value \"2\")\n" +
				"        (eq pcori_dsp_interventi.gen_rx.value \"1\")\n" +
				"        (eq pcori_dsp_interventi.gen_meds_swallow.value \"2\")\n" +
				"        (eq pcori_dsp_interventi.gen_meds_swallow.value \"1\")\n" +
				"        (eq pcori_dsp_interventi.well_foods.value \"2\")\n" +
				"        (eq pcori_dsp_interventi.well_foods.value \"1\")\n" +
				"        (eq pcori_dsp_interventi.well_habits.value \"2\")\n" +
				"    )}} \n" +
				"        <hr></hr>\n" +
				"        <h2>Life Skills</h2>\n" +
				"    {{else}} {{#if (or\n" +
				"        (eq pcori_dsp_interventi.well_habits.value \"1\")\n" +
				"        (eq pcori_dsp_interventi.well_911.value \"2\")\n" +
				"        (eq pcori_dsp_interventi.well_911.value \"1\")\n" +
				"        (eq pcori_dsp_interventi.well_sleep.value \"2\")\n" +
				"        (eq pcori_dsp_interventi.well_sleep.value \"1\")\n" +
				"        (eq pcori_dsp_interventi.well_exercise.value \"2\")\n" +
				"        (eq pcori_dsp_interventi.well_exercise.value \"1\")\n" +
				"        (eq pcori_dsp_interventi.gen_id.value \"2\")\n" +
				"        (eq pcori_dsp_interventi.gen_id.value \"1\")\n" +
				"        (eq pcori_dsp_interventi.gen_stranger.value \"2\")\n" +
				"        (eq pcori_dsp_interventi.gen_stranger.value \"1\")\n" +
				"        (eq pcori_dsp_interventi.self_public_transport.value \"2\")\n" +
				"        (eq pcori_dsp_interventi.self_public_transport.value \"1\")\n" +
				"        (eq pcori_dsp_interventi.self_chores.value \"2\")\n" +
				"        (eq pcori_dsp_interventi.self_chores.value \"1\")\n" +
				"        (eq pcori_dsp_interventi.self_period.value \"2\")\n" +
				"        (eq pcori_dsp_interventi.self_period.value \"1\")\n" +
				"        (eq pcori_dsp_interventi.self_privacy.value \"2\")\n" +
				"        (eq pcori_dsp_interventi.self_privacy.value \"1\")\n" +
				"    )}} \n" +
				"        <hr></hr>\n" +
				"        <h2>Life Skills</h2>\n" +
				"    {{else}} {{#if (or\n" +
				"        (eq pcori_dsp_interventi.self_dentist.value \"2\")\n" +
				"        (eq pcori_dsp_interventi.self_dentist.value \"1\")\n" +
				"        (eq pcori_dsp_interventi.self_restroom.value \"2\")\n" +
				"        (eq pcori_dsp_interventi.self_restroom.value \"1\")\n" +
				"        (eq pcori_dsp_interventi.self_dress.value \"2\")\n" +
				"        (eq pcori_dsp_interventi.self_dress.value \"1\")\n" +
				"        (eq pcori_dsp_interventi.self_bathe.value \"2\")\n" +
				"        (eq pcori_dsp_interventi.self_bathe.value \"1\")\n" +
				"        (eq pcori_dsp_interventi.self_meals.value \"2\")\n" +
				"        (eq pcori_dsp_interventi.self_meals.value \"1\")\n" +
				"        (eq pcori_dsp_interventi.self_laundry.value \"2\")\n" +
				"        (eq pcori_dsp_interventi.self_laundry.value \"1\")\n" +
				"        (eq pcori_dsp_interventi.know_insurance.value \"2\")\n" +
				"        (eq pcori_dsp_interventi.know_insurance.value \"1\")\n" +
				"        (eq pcori_dsp_interventi.know_plan.value \"2\")\n" +
				"        (eq pcori_dsp_interventi.know_plan.value \"1\")\n" +
				"        (eq pcori_dsp_interventi.know_adults.value \"2\")\n" +
				"        (eq pcori_dsp_interventi.know_adults.value \"1\")\n" +
				"    )}}\n" +
				"        <hr></hr>\n" +
				"        <h2>Life Skills</h2>\n" +
				"    {{/if}}{{/if}}{{/if}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.gen_md_phone.value \"==\" \"2\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">&#x25cf;</td>\n" +
				"                <td>\n" +
				"                    <p>{{pcori_dsp_interventi.first_name}}’s caregiver would like for {{pcori_dsp_interventi.first_name}} to learn where to find {{pcori_dsp_interventi.hishertheir}} doctor’s phone number.</p>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.gen_md_talk.value \"==\" \"2\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">&#x25cf;</td>\n" +
				"                <td>\n" +
				"                    <p>{{pcori_dsp_interventi.first_name}}’s caregiver would like for {{pcori_dsp_interventi.first_name}} to learn how to ask questions of {{pcori_dsp_interventi.hishertheir}} doctors.</p>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.gen_sx_describe.value \"==\" \"2\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">&#x25cf;</td>\n" +
				"                <td>\n" +
				"                    <p>{{pcori_dsp_interventi.first_name}}’s caregiver would like for {{pcori_dsp_interventi.first_name}} to learn how to describe how {{pcori_dsp_interventi.heshe}} is feeling to {{pcori_dsp_interventi.hishertheir}} doctor.</p>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.gen_meds.value \"==\" \"2\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">&#x25cf;</td>\n" +
				"                <td>\n" +
				"                    <p>{{pcori_dsp_interventi.first_name}}’s caregiver would like for {{pcori_dsp_interventi.first_name}} to learn how to find {{pcori_dsp_interventi.hishertheir}}  medications.</p>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.gen_meds_reason.value \"==\" \"2\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">&#x25cf;</td>\n" +
				"                <td>\n" +
				"                    <p>{{pcori_dsp_interventi.first_name}}’s caregiver would like for {{pcori_dsp_interventi.first_name}} to learn what each medication is for.</p>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.gen_meds_take.value \"==\" \"2\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">&#x25cf;</td>\n" +
				"                <td>\n" +
				"                    <p>{{pcori_dsp_interventi.first_name}}’s caregiver would like for {{pcori_dsp_interventi.first_name}} to learn how to take {{pcori_dsp_interventi.hishertheir}}  medications every day on {{pcori_dsp_interventi.hishertheir}} own.</p>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.gen_rx.value \"==\" \"2\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">&#x25cf;</td>\n" +
				"                <td>\n" +
				"                    <p>{{pcori_dsp_interventi.first_name}}’s caregiver would like for {{pcori_dsp_interventi.first_name}} to learn how to refill {{pcori_dsp_interventi.hishertheir}}  prescriptions on {{pcori_dsp_interventi.hishertheir}}  own.</p>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.gen_meds_swallow.value \"==\" \"2\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">&#x25cf;</td>\n" +
				"                <td>\n" +
				"                    <p>{{pcori_dsp_interventi.first_name}}’s caregiver would like for {{pcori_dsp_interventi.first_name}} to learn how to swallow whole pills.</p>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.well_foods.value \"==\" \"2\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">&#x25cf;</td>\n" +
				"                <td>\n" +
				"                    <p>{{pcori_dsp_interventi.first_name}}’s caregiver would like for {{pcori_dsp_interventi.first_name}} to learn about the differences between healthy and unhealthy foods.</p>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.well_habits.value \"==\" \"2\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">&#x25cf;</td>\n" +
				"                <td>\n" +
				"                    <p>{{pcori_dsp_interventi.first_name}}’s caregiver would like for {{pcori_dsp_interventi.first_name}} to learn about the risks of alcohol, drugs, and tobacco use.</p>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.well_911.value \"==\" \"2\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">&#x25cf;</td>\n" +
				"                <td>\n" +
				"                    <p>{{pcori_dsp_interventi.first_name}}’s caregiver would like for {{pcori_dsp_interventi.first_name}} to learn how to call 911 if there is an emergency.</p>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.well_sleep.value \"==\" \"2\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">&#x25cf;</td>\n" +
				"                <td>\n" +
				"                    <p>{{pcori_dsp_interventi.first_name}}’s caregiver would like for {{pcori_dsp_interventi.first_name}} to be able to sleep 7 to 8 hours per night./p>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.well_exercise.value \"==\" \"2\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">&#x25cf;</td>\n" +
				"                <td>\n" +
				"                    <p>{{pcori_dsp_interventi.first_name}}’s caregiver would like for {{pcori_dsp_interventi.first_name}} to exercise regularly.</p>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.gen_id.value \"==\" \"2\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">&#x25cf;</td>\n" +
				"                <td>\n" +
				"                    <p>{{pcori_dsp_interventi.first_name}}’s caregiver would like for {{pcori_dsp_interventi.first_name}} to learn how to provide {{pcori_dsp_interventi.hishertheir}}  personal information when needed. </p>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.gen_stranger.value \"==\" \"2\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">&#x25cf;</td>\n" +
				"                <td>\n" +
				"                    <p>{{pcori_dsp_interventi.first_name}}’s caregiver would like for {{pcori_dsp_interventi.first_name}} to learn how to tell the difference between a stranger and a friend.</p>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.self_public_transport.value \"==\" \"2\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">&#x25cf;</td>\n" +
				"                <td>\n" +
				"                    <p>{{pcori_dsp_interventi.first_name}}’s caregiver would like for {{pcori_dsp_interventi.first_name}} to learn how to use public transportation on {{pcori_dsp_interventi.hishertheir}}  own.</p>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.self_chores.value \"==\" \"2\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">&#x25cf;</td>\n" +
				"                <td>\n" +
				"                    <p>{{pcori_dsp_interventi.first_name}}’s caregiver would like for {{pcori_dsp_interventi.first_name}} to learn how to do household chores.</p>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.self_period.value \"==\" \"2\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">&#x25cf;</td>\n" +
				"                <td>\n" +
				"                    <p>{{pcori_dsp_interventi.first_name}}’s caregiver would like for {{pcori_dsp_interventi.first_name}} to learn how to manage her period.</p>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.self_privacy.value \"==\" \"2\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">&#x25cf;</td>\n" +
				"                <td>\n" +
				"                    <p>{{pcori_dsp_interventi.first_name}}’s caregiver would like for {{pcori_dsp_interventi.first_name}} to understand sexual boundaries and privacy.</p>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.self_dentist.value \"==\" \"2\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">&#x25cf;</td>\n" +
				"                <td>\n" +
				"                    <p>{{pcori_dsp_interventi.first_name}}’s caregiver would like for {{pcori_dsp_interventi.first_name}} to learn how to brush {{pcori_dsp_interventi.hishertheir}}  teeth on {{pcori_dsp_interventi.hishertheir}}  own.</p>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.self_restroom.value \"==\" \"2\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">&#x25cf;</td>\n" +
				"                <td>\n" +
				"                    <p>{{pcori_dsp_interventi.first_name}}’s caregiver would like for {{pcori_dsp_interventi.first_name}} to be able to use a public restroom on {{pcori_dsp_interventi.hishertheir}}  own.</p>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.self_dress.value \"==\" \"2\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">&#x25cf;</td>\n" +
				"                <td>\n" +
				"                    <p>{{pcori_dsp_interventi.first_name}}’s caregiver would like for {{pcori_dsp_interventi.first_name}} be able to dress {{pcori_dsp_interventi.himherthem}}self.</p>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.self_bathe.value \"==\" \"2\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">&#x25cf;</td>\n" +
				"                <td>\n" +
				"                    <p>{{pcori_dsp_interventi.first_name}}’s caregiver would like for {{pcori_dsp_interventi.first_name}} to be able to bathe/shower {{pcori_dsp_interventi.himherthem}} self.</p>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.self_meals.value \"==\" \"2\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">&#x25cf;</td>\n" +
				"                <td>\n" +
				"                    <p>{{pcori_dsp_interventi.first_name}}’s caregiver would like for {{pcori_dsp_interventi.first_name}} to prepare {{pcori_dsp_interventi.hishertheir}}  own meals.</p>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.self_laundry.value \"==\" \"2\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">&#x25cf;</td>\n" +
				"                <td>\n" +
				"                    <p>{{pcori_dsp_interventi.first_name}}’s caregiver would like for {{pcori_dsp_interventi.first_name}} to be able to do {{pcori_dsp_interventi.hishertheir}}  own laundry.</p>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.know_insurance.value \"==\" \"2\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">&#x25cf;</td>\n" +
				"                <td>\n" +
				"                    <p>{{pcori_dsp_interventi.first_name}}’s caregiver would like for {{pcori_dsp_interventi.first_name}} to learn how to find {{pcori_dsp_interventi.hishertheir}}  insurance card.</p>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.know_plan.value \"==\" \"2\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">&#x25cf;</td>\n" +
				"                <td>\n" +
				"                    <p>{{pcori_dsp_interventi.first_name}}’s caregiver would like for {{pcori_dsp_interventi.first_name}} to have a plan for what {{pcori_dsp_interventi.heshe}}  will do after finishing high school.</p>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.know_adults.value \"==\" \"2\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">&#x25cf;</td>\n" +
				"                <td>\n" +
				"                    <p>{{pcori_dsp_interventi.first_name}}’s caregiver would like for {{pcori_dsp_interventi.first_name}} to name at least two adults {{pcori_dsp_interventi.heshe}}  can ask for help when needed.</p>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.gen_md_phone.value \"==\" \"1\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">&#x25cf;</td>\n" +
				"                <td>\n" +
				"                    <p>{{pcori_dsp_interventi.first_name}}’s caregiver would like for {{pcori_dsp_interventi.first_name}} to learn where to find {{pcori_dsp_interventi.hishertheir}} doctor’s phone number.</p>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.gen_md_talk.value \"==\" \"1\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">&#x25cf;</td>\n" +
				"                <td>\n" +
				"                    <p>{{pcori_dsp_interventi.first_name}}’s caregiver would like for {{pcori_dsp_interventi.first_name}} to learn how to ask questions of {{pcori_dsp_interventi.hishertheir}} doctors.</p>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.gen_sx_describe.value \"==\" \"1\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">&#x25cf;</td>\n" +
				"                <td>\n" +
				"                    <p>{{pcori_dsp_interventi.first_name}}’s caregiver would like for {{pcori_dsp_interventi.first_name}} to learn how to describe how {{pcori_dsp_interventi.heshe}} is feeling to {{pcori_dsp_interventi.hishertheir}} doctor.</p>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.gen_meds.value \"==\" \"1\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">&#x25cf;</td>\n" +
				"                <td>\n" +
				"                    <p>{{pcori_dsp_interventi.first_name}}’s caregiver would like for {{pcori_dsp_interventi.first_name}} to learn how to find {{pcori_dsp_interventi.hishertheir}}  medications.</p>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.gen_meds_reason.value \"==\" \"1\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">&#x25cf;</td>\n" +
				"                <td>\n" +
				"                    <p>{{pcori_dsp_interventi.first_name}}’s caregiver would like for {{pcori_dsp_interventi.first_name}} to learn what each medication is for.</p>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.gen_meds_take.value \"==\" \"1\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">&#x25cf;</td>\n" +
				"                <td>\n" +
				"                    <p>{{pcori_dsp_interventi.first_name}}’s caregiver would like for {{pcori_dsp_interventi.first_name}} to learn how to take {{pcori_dsp_interventi.hishertheir}}  medications every day on {{pcori_dsp_interventi.hishertheir}} own.</p>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.gen_rx.value \"==\" \"1\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">&#x25cf;</td>\n" +
				"                <td>\n" +
				"                    <p>{{pcori_dsp_interventi.first_name}}’s caregiver would like for {{pcori_dsp_interventi.first_name}} to learn how to refill {{pcori_dsp_interventi.hishertheir}}  prescriptions on {{pcori_dsp_interventi.hishertheir}}  own.</p>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.gen_meds_swallow.value \"==\" \"1\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">&#x25cf;</td>\n" +
				"                <td>\n" +
				"                    <p>{{pcori_dsp_interventi.first_name}}’s caregiver would like for {{pcori_dsp_interventi.first_name}} to learn how to swallow whole pills.</p>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.well_foods.value \"==\" \"1\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">&#x25cf;</td>\n" +
				"                <td>\n" +
				"                    <p>{{pcori_dsp_interventi.first_name}}’s caregiver would like for {{pcori_dsp_interventi.first_name}} to learn about the differences between healthy and unhealthy foods.</p>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.well_habits.value \"==\" \"1\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">&#x25cf;</td>\n" +
				"                <td>\n" +
				"                    <p>{{pcori_dsp_interventi.first_name}}’s caregiver would like for {{pcori_dsp_interventi.first_name}} to learn about the risks of alcohol, drugs, and tobacco use.</p>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.well_911.value \"==\" \"1\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">&#x25cf;</td>\n" +
				"                <td>\n" +
				"                    <p>{{pcori_dsp_interventi.first_name}}’s caregiver would like for {{pcori_dsp_interventi.first_name}} to learn how to call 911 if there is an emergency.</p>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.well_sleep.value \"==\" \"1\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">&#x25cf;</td>\n" +
				"                <td>\n" +
				"                    <p>{{pcori_dsp_interventi.first_name}}’s caregiver would like for {{pcori_dsp_interventi.first_name}} to be able to sleep 7 to 8 hours per night.</p>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.well_exercise.value \"==\" \"1\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">&#x25cf;</td>\n" +
				"                <td>\n" +
				"                    <p>{{pcori_dsp_interventi.first_name}}’s caregiver would like for {{pcori_dsp_interventi.first_name}} to exercise regularly.</p>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.gen_id.value \"==\" \"1\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">&#x25cf;</td>\n" +
				"                <td>\n" +
				"                    <p>{{pcori_dsp_interventi.first_name}}’s caregiver would like for {{pcori_dsp_interventi.first_name}} to learn how to provide {{pcori_dsp_interventi.hishertheir}}  personal information when needed. </p>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.gen_stranger.value \"==\" \"1\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">&#x25cf;</td>\n" +
				"                <td>\n" +
				"                    <p>{{pcori_dsp_interventi.first_name}}’s caregiver would like for {{pcori_dsp_interventi.first_name}} to learn how to tell the difference between a stranger and a friend.</p>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.self_public_transport.value \"==\" \"1\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">&#x25cf;</td>\n" +
				"                <td>\n" +
				"                    <p>{{pcori_dsp_interventi.first_name}}’s caregiver would like for {{pcori_dsp_interventi.first_name}} to learn how to use public transportation on {{pcori_dsp_interventi.hishertheir}}  own.</p>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.self_chores.value \"==\" \"1\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">&#x25cf;</td>\n" +
				"                <td>\n" +
				"                    <p>{{pcori_dsp_interventi.first_name}}’s caregiver would like for {{pcori_dsp_interventi.first_name}} to learn how to do household chores.</p>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.self_period.value \"==\" \"1\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">&#x25cf;</td>\n" +
				"                <td>\n" +
				"                    <p>{{pcori_dsp_interventi.first_name}}’s caregiver would like for {{pcori_dsp_interventi.first_name}} to learn how to manage her period.</p>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.self_privacy.value \"==\" \"1\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">&#x25cf;</td>\n" +
				"                <td>\n" +
				"                    <p>{{pcori_dsp_interventi.first_name}}’s caregiver would like for {{pcori_dsp_interventi.first_name}} to understand sexual boundaries and privacy.</p>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.self_dentist.value \"==\" \"1\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">&#x25cf;</td>\n" +
				"                <td>\n" +
				"                    <p>{{pcori_dsp_interventi.first_name}}’s caregiver would like for {{pcori_dsp_interventi.first_name}} to learn how to brush {{pcori_dsp_interventi.hishertheir}}  teeth on {{pcori_dsp_interventi.hishertheir}}  own.</p>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.self_restroom.value \"==\" \"1\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">&#x25cf;</td>\n" +
				"                <td>\n" +
				"                    <p>{{pcori_dsp_interventi.first_name}}’s caregiver would like for {{pcori_dsp_interventi.first_name}} to be able to use a public restroom on {{pcori_dsp_interventi.hishertheir}}  own.</p>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.self_dress.value \"==\" \"1\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">&#x25cf;</td>\n" +
				"                <td>\n" +
				"                    <p>{{pcori_dsp_interventi.first_name}}’s caregiver would like for {{pcori_dsp_interventi.first_name}} be able to dress {{pcori_dsp_interventi.himherthem}}self.</p>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.self_bathe.value \"==\" \"1\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">&#x25cf;</td>\n" +
				"                <td>\n" +
				"                    <p>{{pcori_dsp_interventi.first_name}}’s caregiver would like for {{pcori_dsp_interventi.first_name}} to be able to bathe/shower {{pcori_dsp_interventi.himherthem}} self.</p>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.self_meals.value \"==\" \"1\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">&#x25cf;</td>\n" +
				"                <td>\n" +
				"                    <p>{{pcori_dsp_interventi.first_name}}’s caregiver would like for {{pcori_dsp_interventi.first_name}} to prepare {{pcori_dsp_interventi.hishertheir}}  own meals.</p>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.self_laundry.value \"==\" \"1\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">&#x25cf;</td>\n" +
				"                <td>\n" +
				"                    <p>{{pcori_dsp_interventi.first_name}}’s caregiver would like for {{pcori_dsp_interventi.first_name}} to be able to do {{pcori_dsp_interventi.hishertheir}}  own laundry.</p>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.know_insurance.value \"==\" \"1\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">&#x25cf;</td>\n" +
				"                <td>\n" +
				"                    <p>{{pcori_dsp_interventi.first_name}}’s caregiver would like for {{pcori_dsp_interventi.first_name}} to learn how to find {{pcori_dsp_interventi.hishertheir}}  insurance card.</p>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.know_plan.value \"==\" \"1\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">&#x25cf;</td>\n" +
				"                <td>\n" +
				"                    <p>{{pcori_dsp_interventi.first_name}}’s caregiver would like for {{pcori_dsp_interventi.first_name}} to have a plan for what {{pcori_dsp_interventi.heshe}}  will do after finishing high school.</p>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.know_adults.value \"==\" \"1\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">&#x25cf;</td>\n" +
				"                <td>\n" +
				"                    <p>{{pcori_dsp_interventi.first_name}}’s caregiver would like for {{pcori_dsp_interventi.first_name}} to name at least two adults {{pcori_dsp_interventi.heshe}}  can ask for help when needed.</p>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
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
				"    {{#compare pcori_dsp_interventi.adrc.value \"==\" \"1\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">&#x25cf;</td>\n" +
				"                <td>\n" +
				"                    <p>ADRC (Aging and Disability Resource Consortia)</p>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.arc.value \"==\" \"1\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">&#x25cf;</td>\n" +
				"                <td>\n" +
				"                    <p>The Arc organization</p>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.bestbuds.value \"==\" \"1\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">&#x25cf;</td>\n" +
				"                <td>\n" +
				"                    <p>Best Buddies organization</p>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.dsconnect.value \"==\" \"1\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">&#x25cf;</td>\n" +
				"                <td>\n" +
				"                    <p>DS-Connect&reg; Down syndrome registry</p>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.gigi.value \"==\" \"1\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">&#x25cf;</td>\n" +
				"                <td>\n" +
				"                    <p>GiGi’s Playhouse</p>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.gdsf.value \"==\" \"1\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">&#x25cf;</td>\n" +
				"                <td>\n" +
				"                    <p>The Global Down Syndrome Foundation</p>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.lumind.value \"==\" \"1\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">&#x25cf;</td>\n" +
				"                <td>\n" +
				"                    <p>LuMind Research Down Syndrome Foundation</p>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.ndsc.value \"==\" \"1\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">&#x25cf;</td>\n" +
				"                <td>\n" +
				"                    <p>National Down Syndrome Congress (NDSC)</p>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.ndss.value \"==\" \"1\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">&#x25cf;</td>\n" +
				"                <td>\n" +
				"                    <p>National Down Syndrome Society (NDSS)</p>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.specialolympics.value \"==\" \"1\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">&#x25cf;</td>\n" +
				"                <td>\n" +
				"                    <p>Special Olympics</p>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.local.value \"==\" \"2\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">&#x25cf;</td>\n" +
				"                <td>\n" +
				"                    <p>We provided a link to access a list of all the Down syndrome organizations in the different states in the USA</p>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.guardianship.value \"==\" \"1\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">&#x25cf;</td>\n" +
				"                <td>\n" +
				"                    <p>We provided a link to specific book pages discussing different guardianship options in the USA</p>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.ssi_2.value \"==\" \"1\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">&#x25cf;</td>\n" +
				"                <td>\n" +
				"                    <p>We provided a link to resources for adults with disabilities applying for social security</p>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.able_2.value \"==\" \"1\"}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">&#x25cf;</td>\n" +
				"                <td>\n" +
				"                    <p>We provided resources on setting up a special needs trusts  or an ABLE account</p>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/compare}}\n" +
				"\n" +
				"    {{#compare pcori_dsp_interventi.further_info.value \"==\" \"0\"}}\n" +
				"    {{#if (or\n" +
				"        (eq pcori_dsp_interventi.ds_dx1.value \"1\")\n" +
				"        (eq pcori_dsp_interventi.ds_dx2.value \"1\")\n" +
				"        (eq pcori_dsp_interventi.ds_dx3.value \"1\")\n" +
				"    )}}\n" +
				"        <table class=\"table-checklist\">\n" +
				"            <tr>\n" +
				"                <td class=\"td-checkbox\">&#x25cf;</td>\n" +
				"                <td>\n" +
				"                    <p>{{pcori_dsp_interventi.first_name}}’s caregiver requested information on {{pcori_dsp_interventi.first_name}}’s genetic diagnosis</p>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    {{/if}}\n" +
				"    {{/compare}}\n" +
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
				"{{/compare}}");



    }
}
