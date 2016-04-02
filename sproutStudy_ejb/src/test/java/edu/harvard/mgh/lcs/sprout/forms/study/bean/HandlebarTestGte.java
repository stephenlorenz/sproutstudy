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



			String json = " {\"sprout\":{\"userId\":\"3861812@mgh\",\"inboxId\":\"995\",\"instanceId\":\"9BBC4C66-A985-41D3-9FBF-3467663E4DB7\",\"currentStep\":\"step21\",\"currentInput\":\"36091\",\"validationStatus\":\"\",\"initStep\":\"step21\",\"initInput\":\"initialized\",\"submissionDate\":\"February 26, 2016 2:15 PM\",\"expirationDate\":\"\",\"lockKey\":\"ACDCAC96-1A2D-401C-8A45-2E233405F8A0\"},\"form\":{\"id\":\"398\",\"version\":\"39\",\"mode\":\"AUTOSAVE\"},\"frm_nuclear_cardiolo_1\":{\"procedure_name1\":\"STRESS TEST EXERCISE\",\"aborted\":\"\",\"order_no\":\"200017\",\"procedure_code\":\"STRESS100\",\"pt_name_grid\":[{\"patient_last_name\":\"Oe-test\"},{\"patent_first_name\":\"George\"},{\"patient_middle_initial\":\"\"}],\"mrn\":\"3861812\",\"epic_dob\":\"19350101\",\"dob\":\"01/01/1935\",\"age\":\"81\",\"gender\":{\"name\":\"Male\",\"value\":\"M\"},\"height\":\"60\",\"weight\":\"130\",\"weight_kg\":\"59.0\",\"bsa\":\"1.6\",\"bmi\":\"25\",\"in_out\":{\"name\":\"Inpatient\",\"value\":\"in\"},\"pt_location_grid\":[{\"patient_location\":{\"name\":\"\",\"value\":\"\"}},{\"floor\":\"\"},{\"lab\":{\"name\":\"\",\"value\":\"\"}}],\"fund_number\":\"\",\"stress_start_time\":\"\",\"ordering_md_first\":\"HENRY\",\"ordering_md_last\":\"GEWIRTZ\",\"ordering_md\":\"HENRY GEWIRTZ\",\"rendering_provider\":{\"name\":\"\",\"value\":\"\"},\"billing_provider\":{\"name\":\"\",\"value\":null},\"staff_nuclear_cardiologist\":{\"name\":\"Ahmed Tawakol, MD\",\"value\":\"at018\"},\"staff_radiologist\":{\"name\":\"\",\"value\":\"\"},\"add_rnmd\":\"\",\"stress_tester\":\"\",\"nuclear_tech\":\"\",\"typed_by\":\"\",\"purpose_grid\":[{\"p_arrhythmia\":{\"name\":\"Assess arryhthmia\",\"value\":\"1\"}},{\"p_angio\":{\"name\":\"Angiographic correlation\",\"value\":\"1\"}},{\"p_baseline\":{\"name\":\"Baseline exercise\",\"value\":\"\"}},{\"p_chemo\":{\"name\":\"Chemotherapy\",\"value\":\"\"}},{\"p_chest_pain\":{\"name\":\"Chest pain\",\"value\":\"\"}},{\"p_diagnosis_ischemia\":{\"name\":\"Diagnosis of ischemia\",\"value\":\"\"}},{\"p_functional\":{\"name\":\"Evaluate functional capacity\",\"value\":\"\"}},{\"p_evaluate_ischemia\":{\"name\":\"Evaluate ischemia\",\"value\":\"\"}},{\"p_lvfunction\":{\"name\":\"Evaluate LV function\",\"value\":\"\"}},{\"p_multiple_risks\":{\"name\":\"Evaluate multiple risks\",\"value\":\"\"}},{\"p_fup_cabg\":{\"name\":\"Follow-up CABG\",\"value\":\"\"}},{\"p_fup_mi\":{\"name\":\"Follow-up MI\",\"value\":\"\"}},{\"p_fup_ptca\":{\"name\":\"Follow-up PTCA/Stent\",\"value\":\"\"}},{\"p_fup_preop\":{\"name\":\"Preop (non-cardiac)\",\"value\":\"\"}},{\"p_research\":{\"name\":\"Research\",\"value\":\"\"}},{\"p_screening\":{\"name\":\"Screening\",\"value\":\"\"}},{\"p_qtc\":{\"name\":\"Assess QTc, rest and stress\",\"value\":\"\"}},{\"p_viability\":{\"name\":\"Assess viability\",\"value\":\"\"}}],\"risk_factors_composite\":[{\"rf_diabetes\":{\"name\":\"Diabetes\",\"value\":\"1\"}},{\"rf_dyslipidemia\":{\"name\":\"Dyslipidemia\",\"value\":\"\"}},{\"rf_hypertension\":{\"name\":\"Hypertension\",\"value\":\"\"}},{\"rf_family_history\":{\"name\":\"Family History\",\"value\":\"\"}},{\"rf_smoking_past\":{\"name\":\"Smoking, past\",\"value\":\"\"}},{\"rf_smoking_pres\":{\"name\":\"Smoking, present\",\"value\":\"\"}},{\"rf_coronary_ca\":{\"name\":\"Coronary calcium\",\"value\":\"1\"}},{\"rf_hscrp\":{\"name\":\"Elevated hsCRP\",\"value\":\"\"}},{\"rf_menopause\":{\"name\":\"Menopause\",\"value\":\"\"}},{\"rf_obesity\":{\"name\":\"Obesity\",\"value\":\"\"}}],\"history_composite\":[{\"hx_mi_current\":{\"name\":\"MI, current\",\"value\":\"\"}},{\"hx_mi_old\":{\"name\":\"MI, old\",\"value\":\"\"}},{\"hx_cp_atypical\":{\"name\":\"CP, atypical\",\"value\":\"\"}},{\"hx_cp_typical\":{\"name\":\"CP, typical\",\"value\":\"\"}},{\"hx_angina_atypical\":{\"name\":\"Atypical angina\",\"value\":\"\"}},{\"hx_angina_typical\":{\"name\":\"Typical angina\",\"value\":\"\"}},{\"hx_dyspnea\":{\"name\":\"Dyspnea\",\"value\":\"\"}},{\"hx_lung_disease\":{\"name\":\"Lung disease\",\"value\":\"\"}},{\"hx_chf\":{\"name\":\"CHF\",\"value\":\"\"}},{\"hx_palpitations\":{\"name\":\"Palpitations\",\"value\":\"\"}},{\"hx_dizzy\":{\"name\":\"Lightheaded/dizzy\",\"value\":\"\"}},{\"hx_substance_abuse\":{\"name\":\"Substance abuse\",\"value\":\"\"}},{\"hx_syncope\":{\"name\":\"Syncope\",\"value\":\"\"}},{\"hx_presyncope\":{\"name\":\"Pre-syncope\",\"value\":\"\"}},{\"hx_arrhythmia\":{\"name\":\"Arrhythmia\",\"value\":\"\"}},{\"hx_pvd\":{\"name\":\"PVD/Carotid disease\",\"value\":\"\"}},{\"hx_device\":{\"name\":\"Implantable device\",\"value\":\"\"}},{\"hx_fatigue\":{\"name\":\"Fatigue\",\"value\":\"\"}},{\"hx_cerebro_disease\":{\"name\":\"Cerebrovascular disease\",\"value\":\"\"}},{\"hx_thrombolysis\":{\"name\":\"Thrombolysis\",\"value\":\"\"}},{\"hx_valve_disease\":{\"name\":\"Valvular abnormalities\",\"value\":\"\"}},{\"hx_kidney_disease\":{\"name\":\"Kidney disease\",\"value\":\"\"}},{\"hx_other_cardiac_disease\":{\"name\":\"Other heart disease\",\"value\":\"\"}},{\"hx_cancer\":{\"name\":\"Cancer\",\"value\":\"\"}},{\"hx_etoh\":{\"name\":\"ETOH\",\"value\":\"\"}},{\"hx_seizures\":{\"name\":\"Seizures\",\"value\":\"\"}},{\"hx_viagra\":{\"name\":\"Viagra\",\"value\":\"\"}},{\"hx_viagra_last_dose\":\"\"}],\"cath\":{\"name\":\"Cath\",\"value\":\"\"},\"cath_composite\":[{\"cath_date\":\"\"},{\"cath_norm\":{\"name\":\"Normal Cors\",\"value\":\"\"}},{\"cath_cad\":{\"name\":\"CAD\",\"value\":\"\"}},{\"cath_lad\":{\"name\":\"LAD\",\"value\":\"\"}},{\"cath_cx\":{\"name\":\"Cx\",\"value\":\"\"}},{\"cath_rca\":{\"name\":\"RCA\",\"value\":\"\"}},{\"cath_lm\":{\"name\":\"LM\",\"value\":\"\"}}],\"cabg\":{\"name\":\"CABG\",\"value\":\"\"},\"cabg_composite\":[{\"cabg_date\":\"\"},{\"cabg_lad\":{\"name\":\"LAD\",\"value\":\"\"}},{\"cabg_cx\":{\"name\":\"Cx\",\"value\":\"\"}},{\"cabg_rca\":{\"name\":\"RCA\",\"value\":\"\"}},{\"cabg_lm\":{\"name\":\"LM\",\"value\":\"\"}}],\"ptca\":{\"name\":\"PTCA/Stent\",\"value\":\"\"},\"ptca_composite\":[{\"ptca_date\":\"\"},{\"ptca_lad\":{\"name\":\"LAD\",\"value\":\"\"}},{\"ptca_cx\":{\"name\":\"Cx\",\"value\":\"\"}},{\"ptca_rca\":{\"name\":\"RCA\",\"value\":\"\"}},{\"ptca_lm\":{\"name\":\"LM\",\"value\":\"\"}}],\"primary_dx\":{\"name\":\"Abnormal CV study\",\"value\":\"abcv\"},\"secondary_dx\":{\"name\":\"\",\"value\":\"\"},\"other_dx\":\"\",\"medications\":{\"name\":\"No\",\"value\":\"0\"},\"min_medication_name\":\"\",\"min_medication_code\":\"\",\"medication_code\":\"\",\"medications_other\":\"\",\"pain_in_other\":\"\",\"pain_refer_composite\":[{\"pain_to_note\":{\"name\":\"Progress note\",\"value\":\"\"}},{\"pain_to_chart\":{\"name\":\"Medication records\",\"value\":\"\"}},{\"pain_to_flow\":{\"name\":\"Flow chart\",\"value\":\"\"}}],\"pain_perf_by\":\"\",\"pain_location\":{\"name\":\"\",\"value\":\"\"},\"pain_comment\":\"\",\"pain_ref_composite\":[{\"pain_ref_pcp\":{\"name\":\"PCP\",\"value\":\"\"}},{\"pain_ref_ed\":{\"name\":\"ED\",\"value\":\"\"}},{\"pain_ref_other\":{\"name\":\"Other\",\"value\":\"\"}}],\"pain_meds\":{\"name\":\"Medication\",\"value\":\"\"},\"pain_treat_other\":\"\",\"pain_reasses_composite\":[{\"pain_reasses_des\":{\"name\":\"Desired\",\"value\":\"\"}},{\"pain_reasses_unded\":{\"name\":\"Undesired\",\"value\":\"\"}},{\"pain_reasses_early\":{\"name\":\"Too early\",\"value\":\"\"}}],\"pain_reasses_comment\":\"\",\"procedure_name\":\"STRESS TEST EXERCISE\",\"order_date_epic\":\"20150805103411\",\"order_date\":\"Aug 5, 2015\",\"order_time\":\"10:34 am\",\"accession_number\":\"T100017\",\"procedure\":\"procNIETT\",\"procedure_type\":{\"name\":\"Standard Bruce\",\"value\":\"2\"},\"gbps_dose\":\"\",\"rest_dose\":\"\",\"stress_dose\":\"\",\"rest_perf_dose\":\"\",\"fdg_dose\":\"\",\"insulin\":{\"name\":\"Insulin\",\"value\":\"\"},\"units\":\"\",\"max_dose\":{\"name\":\"\",\"value\":\"\"},\"max_dose_frac\":\"0\",\"time_max_dose_min\":\"\",\"time_max_dose_sec\":\"\",\"max_dose_min_dec\":\"0.00\",\"max_dose_calc\":\"0\",\"dob_cum_dose_calc\":\"0\",\"dipyridamole_dose_calc\":\"33\",\"atropine_dose\":\"\",\"iv_aminophylline\":\"\",\"procedural_comment\":\"\",\"restonly_hr\":\"\",\"restonly_bp_grid\":[{\"restonly_sbp\":\"\"},{\"restonly_dbp\":\"\"}],\"restonly_comment\":\"\",\"ecg_rhythm_grid\":[{\"ecg_nsr\":{\"name\":\"NSR\",\"value\":\"1\"}},{\"ecg_ap\":{\"name\":\"A-Paced\",\"value\":\"1\"}},{\"ecg_vp\":{\"name\":\"V-Paced\",\"value\":\"\"}},{\"ecg_avp\":{\"name\":\"AV-Paced\",\"value\":\"\"}},{\"ecg_apc\":{\"name\":\"APCs\",\"value\":\"1\"}},{\"ecg_pvc\":{\"name\":\"PVCs\",\"value\":\"\"}},{\"ecg_afib\":{\"name\":\"AFib\",\"value\":\"\"}},{\"ecg_atfl\":{\"name\":\"Atrial Flutter\",\"value\":\"\"}},{\"ecg_svt\":{\"name\":\"SVT\",\"value\":\"\"}},{\"ecg_aivr\":{\"name\":\"AIVR\",\"value\":\"\"}},{\"ecg_ab\":{\"name\":\"Sinus Bradycardia\",\"value\":\"\"}},{\"ecg_1avb\":{\"name\":\"1degree AVB\",\"value\":\"\"}},{\"ecg_m1\":{\"name\":\"Mobitz I\",\"value\":\"\"}},{\"ecg_m2\":{\"name\":\"Mobitz II\",\"value\":\"\"}}],\"ecg_other_grid\":[{\"ecg_twnl\":{\"name\":\"TWNL\",\"value\":\"\"}},{\"ecg_imi\":{\"name\":\"IMI\",\"value\":\"\"}},{\"ecg_ami\":{\"name\":\"AMI\",\"value\":\"\"}},{\"ecg_tpmi\":{\"name\":\"TPMI\",\"value\":\"\"}},{\"ecg_latmi\":{\"name\":\"LatMI\",\"value\":\"\"}},{\"ecg_asmi\":{\"name\":\"ASMI\",\"value\":\"\"}},{\"ecg_ipmi\":{\"name\":\"IPMI\",\"value\":\"\"}},{\"ecg_lbbb\":{\"name\":\"LBBB\",\"value\":\"\"}},{\"ecg_rbbb\":{\"name\":\"RBBB\",\"value\":\"\"}},{\"ecg_ivcd\":{\"name\":\"IVCD\",\"value\":\"\"}},{\"ecg_lvh\":{\"name\":\"LVH\",\"value\":\"\"}},{\"ecg_rvh\":{\"name\":\"RVH\",\"value\":\"\"}},{\"ecg_bvh\":{\"name\":\"BVH\",\"value\":\"\"}},{\"ecg_lae\":{\"name\":\"LAE\",\"value\":\"\"}},{\"ecg_rae\":{\"name\":\"RAE\",\"value\":\"\"}},{\"ecg_bae\":{\"name\":\"BAE\",\"value\":\"\"}},{\"ecg_qwaves\":{\"name\":\"Q waves\",\"value\":\"\"}},{\"ecg_sttwa\":{\"name\":\"ST-T wave abnormalities\",\"value\":\"\"}},{\"ecg_prwp\":{\"name\":\"PRWP\",\"value\":\"\"}},{\"ecg_lph\":{\"name\":\"LPH\",\"value\":\"\"}},{\"ecg_lahb\":{\"name\":\"LAHB\",\"value\":\"\"}},{\"ecg_twi\":{\"name\":\"T wave inversions\",\"value\":\"\"}},{\"ecg_lad\":{\"name\":\"LAD\",\"value\":\"\"}},{\"ecg_rad\":{\"name\":\"RAD\",\"value\":\"\"}}],\"ecg_comments\":\"\",\"max_st_change_calc\":\"\",\"stress_ecg_comments\":\"\",\"arrhythmias\":{\"name\":\"No Arrhythmia\",\"value\":\"\"},\"arrhythmia_apc_occ\":{\"name\":\"Occasional\",\"value\":\"\"},\"arrhythmia_apc_frequent\":{\"name\":\"Frequent\",\"value\":\"\"},\"arrhythmia_vpc_occ\":{\"name\":\"Occasional\",\"value\":\"\"},\"arrhythmia_vpc_frequent\":{\"name\":\"Frequent\",\"value\":\"\"},\"arrhythmia_vt_sust\":{\"name\":\"Sustained (greater than 30 secs)\",\"value\":\"\"},\"arrhythmia_vt_non\":{\"name\":\"Non-sustained (less than 30 secs)\",\"value\":\"\"},\"arrhythmia_beats\":\"\",\"arrhythmia_rate\":\"\",\"arrhythmia_vea_coup\":{\"name\":\"Couplets\",\"value\":\"\"},\"arrhythmia_vea_bitri\":{\"name\":\"Bi/Tri Gemini\",\"value\":\"\"},\"resting_hr\":\"\",\"resting_bp_grid\":[{\"resting_sbp\":\"\"},{\"resting_dbp\":\"\"}],\"peak_hr\":\"\",\"peak_bp_grid\":[{\"peak_sbp\":\"\"},{\"peak_dbp\":\"\"}],\"min_bp_grid\":[{\"minimum_sbp\":\"\"},{\"minimum_dbp\":\"\"}],\"mphr\":\"0\",\"injection_time\":\"\",\"stress_chest_pain_composite\":[{\"cp_no\":{\"name\":\"Did not occur\",\"value\":\"\"}},{\"cp_yes\":{\"name\":\"Occurred\",\"value\":\"\"}},{\"cp_same\":{\"name\":\"Same as presenting pain\",\"value\":\"\"}},{\"cp_different\":{\"name\":\"Different from presenting pain\",\"value\":\"\"}},{\"cp_angina\":{\"name\":\"c/w angina\",\"value\":\"\"}},{\"cp_tng\":{\"name\":\"Required TNG\",\"value\":\"\"}},{\"cp_no_tng\":{\"name\":\"Did not require TNG\",\"value\":\"\"}}],\"angina_index\":\"0\",\"cp_onset_min\":\"\",\"cp_onset_sec\":\"\",\"cp_onset_hr\":\"\",\"cp_bp_grid\":[{\"cp_onset_sbp\":\"\"},{\"cp_onset_dbp\":\"\"}],\"peak_double\":\"0\",\"mets\":{\"name\":\"7\",\"value\":\"7\"},\"total_exercise_time_min\":\"\",\"total_exercise_time_sec\":\"\",\"total_exercise_time\":\"0\",\"treadmill_grade\":\"\",\"speed\":\"\",\"end_point_composite\":[{\"ep_arrhythmia\":{\"name\":\"Arrhythmia\",\"value\":\"\"}},{\"ep_chest_pain\":{\"name\":\"Chest pain\",\"value\":\"\"}},{\"ep_claudication\":{\"name\":\"Claudication\",\"value\":\"\"}},{\"ep_completed_protocol\":{\"name\":\"Completed protocol\",\"value\":\"\"}},{\"ep_dyspnea\":{\"name\":\"Dyspnea\",\"value\":\"\"}},{\"ep_fall_bp\":{\"name\":\"Fall in blood pressure\",\"value\":\"\"}},{\"ep_fatigue\":{\"name\":\"Fatigue\",\"value\":\"\"}},{\"ep_leg_fatigue\":{\"name\":\"Leg fatigue\",\"value\":\"\"}},{\"ep_lightheaded\":{\"name\":\"Lightheaded\",\"value\":\"\"}},{\"ep_refusal\":{\"name\":\"Refusal\",\"value\":\"\"}},{\"ep_st_elev\":{\"name\":\"ST elevation\",\"value\":\"\"}},{\"ep_st_dep\":{\"name\":\"ST depression\",\"value\":\"\"}},{\"ep_syncope\":{\"name\":\"Syncope\",\"value\":\"\"}},{\"ep_htn\":{\"name\":\"Hypertension\",\"value\":\"\"}},{\"ep_other\":{\"name\":\"Other\",\"value\":\"\"}},{\"ep_other_detail\":\"\"}],\"stress_data_comments\":\"\",\"procedural_notes\":\"\",\"include_report\":{\"name\":\"Include in report\",\"value\":\"\"},\"interpretation\":{\"name\":\"uninterpretable for ischemia due to entricular pacing\",\"value\":\"un1\"},\"stress_conclusion_req_comment\":\"\",\"stress_conclusion_comment\":\"\",\"calc_duke\":{\"name\":\"Yes, override criterion\",\"value\":\"2\"},\"duke_score\":\"0\",\"estimated_mortality\":\"1.4\",\"lv_ef\":\"\",\"ef_unobtain\":{\"name\":\"EF unobtainable (requires comment)\",\"value\":\"\"},\"lv_normal\":{\"name\":\"Rest LV size normal\",\"value\":\"\"},\"ef_unobtain_comment\":\"\",\"ef_comment\":\"\",\"lv_dilation_rest\":{\"name\":\"\",\"value\":\"\"},\"lv_dilation_stress\":{\"name\":\"\",\"value\":\"\"},\"rv_dilation\":{\"name\":\"\",\"value\":\"\"},\"extra_card\":{\"name\":\"\",\"value\":\"\"},\"imaging_comment\":\"\",\"lv_ef2\":\"\",\"ef_unobtain2\":{\"name\":\"EF unobtainable (requires comment)\",\"value\":\"\"},\"lv_normal2\":{\"name\":\"Rest LV size normal\",\"value\":\"\"},\"ef_unobtain_comment2\":\"\",\"ef_comment2\":\"\",\"non_cardiac_results\":{\"name\":\"\",\"value\":\"\"},\"non_cardiac_comment\":\"\",\"lv_dilation_rest2\":{\"name\":\"\",\"value\":\"\"},\"lv_dilation_stress2\":{\"name\":\"\",\"value\":\"\"},\"rv_dilation2\":{\"name\":\"\",\"value\":\"\"},\"extra_card2\":{\"name\":\"\",\"value\":\"\"},\"imaging_comment2\":\"\",\"all_wnl\":{\"name\":\"\",\"value\":\"\"},\"equivocal\":{\"name\":\"\",\"value\":\"\"},\"lvef\":\"\",\"ef_unobtain_gbps\":{\"name\":\"EF Unobtainable\",\"value\":\"\"},\"ra_wnl\":{\"name\":\"\",\"value\":\"\"},\"ra_grid\":[{\"ra_mild\":{\"name\":\"Enlarged mild\",\"value\":\"\"}},{\"ra_mod\":{\"name\":\"Enlarged moderate\",\"value\":\"\"}},{\"ra_sev\":{\"name\":\"Enlarged severe\",\"value\":\"\"}},{\"ra_akinetic\":{\"name\":\"Akinetic\",\"value\":\"\"}}],\"rv_wnl\":{\"name\":\"\",\"value\":\"\"},\"rv_grid\":[{\"rv_mild\":{\"name\":\"Enlarged mild\",\"value\":\"\"}},{\"rv_mod\":{\"name\":\"Enlarged moderate\",\"value\":\"\"}},{\"rv_sev\":{\"name\":\"Enlarged severe\",\"value\":\"\"}},{\"rv_asyn_ant\":{\"name\":\"Asynergy ant\",\"value\":\"\"}},{\"rv_asyn_inf\":{\"name\":\"Asynergy inf\",\"value\":\"\"}},{\"rv_asyn_sept\":{\"name\":\"Asynergy sept\",\"value\":\"\"}},{\"rv_asyn_lat\":{\"name\":\"Asynergy lat\",\"value\":\"\"}},{\"rv_asyn_glo\":{\"name\":\"Asynergy global\",\"value\":\"\"}}],\"pa_wnl\":{\"name\":\"\",\"value\":\"\"},\"pa_grid\":[{\"pa_mild\":{\"name\":\"Enlarged mild\",\"value\":\"\"}},{\"pa_mod\":{\"name\":\"Enlarged moderate\",\"value\":\"\"}},{\"pa_sev\":{\"name\":\"Enlarged severe\",\"value\":\"\"}}],\"la_wnl\":{\"name\":\"\",\"value\":\"\"},\"la_grid\":[{\"la_mild\":{\"name\":\"Enlarged mild\",\"value\":\"\"}},{\"la_mod\":{\"name\":\"Enlarged moderate\",\"value\":\"\"}},{\"la_sev\":{\"name\":\"Enlarged severe\",\"value\":\"\"}},{\"la_akinetic\":{\"name\":\"Akinetic\",\"value\":\"\"}}],\"lv_wnl\":{\"name\":\"\",\"value\":\"\"},\"lv_grid\":[{\"lv_mild\":{\"name\":\"Enlarged mild\",\"value\":\"\"}},{\"lv_mod\":{\"name\":\"Enlarged moderate\",\"value\":\"\"}},{\"lv_sev\":{\"name\":\"Enlarged severe\",\"value\":\"\"}},{\"lv_asyn_ant\":{\"name\":\"Asynergy ant\",\"value\":\"\"}},{\"lv_asyn_inf\":{\"name\":\"Asynergy inf\",\"value\":\"\"}},{\"lv_asyn_sept\":{\"name\":\"Asynergy sept\",\"value\":\"\"}},{\"lv_asyn_lat\":{\"name\":\"Asynergy lat\",\"value\":\"\"}},{\"lv_asyn_glo\":{\"name\":\"Asynergy global\",\"value\":\"\"}}],\"ao_wnl\":{\"name\":\"\",\"value\":\"\"},\"ao_grid\":[{\"ao_mild\":{\"name\":\"Enlarged mild\",\"value\":\"\"}},{\"ao_mod\":{\"name\":\"Enlarged moderate\",\"value\":\"\"}},{\"ao_sev\":{\"name\":\"Enlarged severe\",\"value\":\"\"}}],\"gbps_comment\":\"\",\"conclusion_template\":{\"name\":\"Ischemia\",\"value\":\"ischem\"},\"conc_ischem_size\":{\"name\":\"small\",\"value\":\"1\"},\"conc_ischem_change\":{\"name\":\"reversible\",\"value\":\"2\"},\"conc_ischem_zone\":{\"name\":\"anterior septal\",\"value\":\"1\"},\"conc_ischem_indicates\":{\"name\":\"\",\"value\":\"\"},\"conc_infarct_zone\":{\"name\":\"\",\"value\":\"\"},\"conc_infarct_prior\":{\"name\":\"\",\"value\":\"\"},\"conc_infarct_severity\":{\"name\":\"\",\"value\":\"\"},\"conc_same_comp\":\"\",\"conc_same_test\":{\"name\":\"\",\"value\":\"\"},\"conc_same_diff\":{\"name\":\"\",\"value\":\"\"},\"conc_worse_comp\":\"\",\"conc_worse_test\":{\"name\":\"\",\"value\":\"\"},\"conc_worse_zone\":{\"name\":\"\",\"value\":\"\"},\"conc_worse_diff\":{\"name\":\"\",\"value\":\"\"},\"conc_better_comp\":\"\",\"conc_better_test\":{\"name\":\"\",\"value\":\"\"},\"conc_better_zone\":{\"name\":\"\",\"value\":\"\"},\"conc_better_status\":{\"name\":\"\",\"value\":\"\"},\"conc_better_diff\":{\"name\":\"\",\"value\":\"\"},\"conc_mild_zone\":{\"name\":\"\",\"value\":\"\"},\"conc_further_comments\":\"\",\"critical_result\":{\"name\":\"No\",\"value\":\"0\"},\"event_date\":\"\",\"event_time\":\"\",\"critical_results_composite\":[{\"st_elevation\":{\"name\":\"ST elevation\",\"value\":\"\"}},{\"vt_vf\":{\"name\":\"VT/VF\",\"value\":\"\"}},{\"ant_ischemia_scan\":{\"name\":\"Anterior ischemia on scan; Suspect severe LAD or left main disease\",\"value\":\"\"}},{\"pos_ecg_scan\":{\"name\":\"Markedly positive ECG + scan ischemia\",\"value\":\"\"}},{\"pos_ecg_clinical\":{\"name\":\"Markedly positive ECG + clinical ischemia\",\"value\":\"\"}}],\"critical_result_comment\":\"\",\"md_name\":\"\",\"md_name2\":\"\",\"md_name3\":\"\",\"md_notified_date\":\"\",\"md_notified_time\":\"\",\"critical_result_notification_comment\":\"\",\"critical_result_verification\":{\"name\":\"The entire critical test result, including the patient's full name and medical record number, was read back by the receiving individual(s) and verified that the test result was correct.\",\"value\":\"\"},\"ready_for_review\":{\"name\":\"Technical portion completed. Ready for Reader review.\",\"value\":\"1\"},\"staff_card_signature_token\":\"eyJwcmluY2lwYWwiOiJhdDAxOCIsInRva2VuIjoiNUVFRURDRDEtODk1MS00MDJELTkxQkItNUQyNEMxNUQzQkFDIiwiZGF0ZVRpbWUiOiIwMi8yNi8yMDE2IDE6MTYgUE0iLCJtYXNrIjo4fQ==\",\"staff_card_signature_uid\":\"at018\",\"staff_card_signature_dateTime\":\"02/26/2016 1:16 PM\",\"staff_rad_signature_token\":\"\",\"staff_rad_signature_uid\":\"\",\"staff_rad_signature_dateTime\":\"\",\"narrative_placeholder\":\"\",\"action_taken\":{\"value\":\"Office\"}}}";
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


		template.append("{{#if frm_nuclear_cardiolo_1.mets.value}}");
		template.append("The patient has {{#compare frm_nuclear_cardiolo_1.mets.name \"<=\" '5'}}reduced{{/compare}}{{#compare frm_nuclear_cardiolo_1.mets.name \">\" '5'}}{{#compare frm_nuclear_cardiolo_1.mets.name \"<=\" '7'}}normal{{/compare}}{{#compare frm_nuclear_cardiolo_1.mets.name \">\" '7'}}{{#compare frm_nuclear_cardiolo_1.mets.name \"<=\" '9'}}good{{/compare}}{{/compare}}{{#compare frm_nuclear_cardiolo_1.mets.name \">\" '9'}}excellent{{/compare}}{{/compare}} exercise capacity (<font color=\"#31708f\" style=\"background-color: #d9edf7\">{{frm_nuclear_cardiolo_1.mets.name}}</font> METS).");
		template.append("{{/if}}");



    }
}
