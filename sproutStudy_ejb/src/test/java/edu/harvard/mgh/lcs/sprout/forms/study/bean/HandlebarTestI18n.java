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

public class HandlebarTestI18n {

	private SecureRandom random = new SecureRandom();

	private StringBuilder template = new StringBuilder();


	@Test
	public void test() {

		init();
		try {

			Handlebars handlebars = new Handlebars();

			handlebars.registerHelpers(new Helpers());

			String json = "{\n" +
					"  \"sprout\": {\n" +
					"    \"userId\": \"1@sprouttransform\",\n" +
					"    \"inboxId\": \"2464\",\n" +
					"    \"instanceId\": \"2B718B77-8F70-45E2-A71A-7D89215833FB\",\n" +
					"    \"currentStep\": \"step0\",\n" +
					"    \"currentInput\": \"39294\",\n" +
					"    \"validationStatus\": \"\",\n" +
					"    \"initStep\": \"step0\",\n" +
					"    \"initInput\": \"initialized\",\n" +
					"    \"submissionDate\": \"1499362893974\",\n" +
					"    \"expirationDate\": \"\",\n" +
					"    \"lockKey\": \"2BA5EA4D-50FC-47D1-8A67-D320258CD726\",\n" +
					"    \"locale\": \"es\"\n" +
					"  },\n" +
					"  \"form\": {\n" +
					"    \"id\": \"1438\",\n" +
					"    \"version\": \"1\",\n" +
					"    \"mode\": \"\"\n" +
					"  },\n" +
					"  \"pcori_dsp_interventi\": {\n" +
					"    \"first_name\": \"Stephen\",\n" +
					"    \"pt_dob\": \"__/__/____\",\n" +
					"    \"pt_age\": \"NaN\",\n" +
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
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"ds_dx4\": {\n" +
					"      \"name\": \"Not Sure\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"comment_1\": \"\",\n" +
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
					"    \"increasing_fatigue\": {\n" +
					"      \"name\": \"Increasing fatigue\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"concerns_about_vision\": {\n" +
					"      \"name\": \"Concerns about vision\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"involuntary_eye_movements\": {\n" +
					"      \"name\": \"Involuntary eye movements\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"ear_infection\": {\n" +
					"      \"name\": \"Chronic ear infection(s)\",\n" +
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
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"nausea\": {\n" +
					"      \"name\": \"Nausea\",\n" +
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
					"      \"name\": \"Passing gas\",\n" +
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
					"      \"name\": \"Difficulty toilet training\",\n" +
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
					"    \"accidents_stool\": {\n" +
					"      \"name\": \"New accidents with stool\",\n" +
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
					"      \"name\": \"Areas of skin that have turned white or lost their color (Vitiligo)\",\n" +
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
					"      \"name\": \"Skin boils under armpits and groins\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"itching\": {\n" +
					"      \"name\": \"Itching\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"torticollis\": {\n" +
					"      \"name\": \"Abnormal twisting of the neck\",\n" +
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
					"      \"name\": \"Delayed puberty\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"poor_growth\": {\n" +
					"      \"name\": \"Poor growth\",\n" +
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
					"      \"name\": \"Seizures\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"ability_change\": {\n" +
					"      \"name\": \"Not able to do things as he or she used to\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"snore\": {\n" +
					"      \"name\": \"Snoring\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"gasp_sleep\": {\n" +
					"      \"name\": \"Gasping, snorting, chokingduring sleep\",\n" +
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
					"      \"name\": \"Falling asleep/nap at school\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"comment_2\": \"\",\n" +
					"    \"sad_depression\": {\n" +
					"      \"name\": \"Sad, downcast unhappy\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"lack_interest_activities\": {\n" +
					"      \"name\": \"Lack of interest in activites\",\n" +
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
					"      \"name\": \"Tense, anxious, worries\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"alone_distress\": {\n" +
					"      \"name\": \"Distressed about being alone\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"distress_separate\": {\n" +
					"      \"name\": \"Distress when separated from familiar person\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"refuses_school\": {\n" +
					"      \"name\": \"Refuses to go to school or activity\",\n" +
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
					"      \"name\": \"Aloof, in his or her own world\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"do_own\": {\n" +
					"      \"name\": \"Prefers to do things on his/her own\",\n" +
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
					"      \"name\": \"Flicks, taps, twirls objects repeatedly\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"talk_self\": {\n" +
					"      \"name\": \"Talks to self or imaginary people\",\n" +
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
					"      \"name\": \"Hurts himself/herself on purpose (e.g. hitting head, biting hands)\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"bangs_head\": {\n" +
					"      \"name\": \"Bangs head\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"irritable\": {\n" +
					"      \"name\": \"Is irritable\",\n" +
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
					"      \"name\": \"Property destruction\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"forgetful\": {\n" +
					"      \"name\": \"Forgetful\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"situation_fear\": {\n" +
					"      \"name\": \"Fear particular situations (e.g. dark, animals)\",\n" +
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
					"    \"comment_3\": \"\",\n" +
					"    \"comment_4\": \"\",\n" +
					"    \"keratoconus\": {\n" +
					"      \"name\": \"Keratoconus\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"strabismus\": {\n" +
					"      \"name\": \"Strabismus\",\n" +
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
					"    \"hirshsprung\": {\n" +
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
					"    \"pmh_fissured_tongue\": {\n" +
					"      \"name\": \"Fissured tongue\",\n" +
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
					"    \"pmh_hair_loss\": {\n" +
					"      \"name\": \"Alopecia areata\",\n" +
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
					"    \"comment_pmh\": \"\",\n" +
					"    \"comment_imm\": \"\",\n" +
					"    \"fruit\": {\n" +
					"      \"name\": \"Lack of fruit or vegetable intake\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"fiber\": {\n" +
					"      \"name\": \"Too little fiber\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"fluids\": {\n" +
					"      \"name\": \"Not enough fluids\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"breastfeed\": {\n" +
					"      \"name\": \"Difficulty bottle or breast feeding\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"beh1\": {\n" +
					"      \"name\": \"Pace of eating\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"beh2\": {\n" +
					"      \"name\": \"Meal-time duration\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"beh3\": {\n" +
					"      \"name\": \"Selectiveness of foods\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"beh4\": {\n" +
					"      \"name\": \"Rigid sensory preferences\",\n" +
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
					"      \"name\": \"Stephen pockets food, meaning he/she leaves food in his/her mouth and does not swallow right away.\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"conc_chew\": {\n" +
					"      \"name\": \"Stephen does not chew his/her food well\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"conc_toofast\": {\n" +
					"      \"name\": \"Stephen eats too fast or puts too much food in his/her mouth at one time.\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"conc_cough\": {\n" +
					"      \"name\": \"Stephen coughs when he/she eats solid food\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"conc_texture\": {\n" +
					"      \"name\": \"Stephen is particular with the texture of certain foods\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"comment_5\": \"\",\n" +
					"    \"sign_language\": {\n" +
					"      \"name\": \"Using sign language to communicate\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"speech_communication\": {\n" +
					"      \"name\": \"Speech and communication skills\",\n" +
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
					"    \"math\": {\n" +
					"      \"name\": \"Math skills\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"reading\": {\n" +
					"      \"name\": \"Reading skills\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"apraxia\": {\n" +
					"      \"name\": \"Apraxia\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"aac_devices\": {\n" +
					"      \"name\": \"Augmentative and alternative communication devices\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"aba_therapy\": {\n" +
					"      \"name\": \"Applied Behavioral Analysis (ABA) Therapy\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"comment_schools\": \"\",\n" +
					"    \"ark\": {\n" +
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
					"    \"gdfs\": {\n" +
					"      \"name\": \"The Global Down Syndrome Foundation\",\n" +
					"      \"value\": \"\"\n" +
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
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"specialolympics\": {\n" +
					"      \"name\": \"Special Olympics\",\n" +
					"      \"value\": \"\"\n" +
					"    },\n" +
					"    \"comment_community\": \"\"\n" +
					"  },\n" +
					"  \"translations\": {\n" +
					"    \"test\": {\n" +
					"      \"en\": \"\\\"This is a test.\\\"\",\n" +
					"      \"es\": \"Esta es una prueba\"\n" +
					"    },\n" +
					"    \"cow\": {\n" +
					"      \"en\": \"c\\\"ow\",\n" +
					"      \"es\": \"vaca\"\n" +
					"    },\n" +
					"    \"dog\": {\n" +
					"      \"en\": \"dog\",\n" +
					"      \"es\": \"perro\"\n" +
					"    },\n" +
					"    \"cat\": {\n" +
					"      \"en\": \"cat\",\n" +
					"      \"es\": \"gato\"\n" +
					"    },\n" +
					"    \"tilde\": {\n" +
					"      \"en\": \"tilde\",\n" +
					"      \"es\": \"ñî\"\n" +
					"    },\n" +
					"    \"wall\": {\n" +
					"      \"en\": \"wall\",\n" +
					"      \"es\": \"pared\"\n" +
					"    },\n" +
					"    \"lamp\": {\n" +
					"      \"en\": \"lamp\",\n" +
					"      \"es\": \"lampara\"\n" +
					"    },\n" +
					"    \"train\": {\n" +
					"      \"en\": \"train\",\n" +
					"      \"es\": \"tren\"\n" +
					"    },\n" +
					"    \"car\": {\n" +
					"      \"en\": \"cat\",\n" +
					"      \"es\": \"coche\"\n" +
					"    },\n" +
					"    \"new\": {\n" +
					"      \"en\": \"new\",\n" +
					"      \"es\": \"noticias\"\n" +
					"    },\n" +
					"    \"hello\": {\n" +
					"      \"en\": \"Hello {{pcori_dsp_interventi.first_name}},\",\n" +
					"      \"es\": \"Hola {{pcori_dsp_interventi.first_name}},\"\n" +
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


		template.append("{{#i18n \"hello\"}}Hello {{pcori_dsp_interventi.first_name}}{{/i18n}}\n" +
				"<br/><br/>\n" +
				"\n" +
				"{{#i18n \"test\"}}This is a test.{{/i18n}}...\n" +
				"\n" +
				"<br/>\n" +
				"<strong>Cat:</strong> {{#i18n \"cat\"}}cat{{/i18n}}...\n" +
				"<br/>\n" +
				"<strong>Cow:</strong> {{#i18n \"cow\"}}cow{{/i18n}}...\n" +
				"<br/>\n" +
				"<strong>Train:</strong> {{#i18n \"train\"}}train{{/i18n}}...");

	}
}
