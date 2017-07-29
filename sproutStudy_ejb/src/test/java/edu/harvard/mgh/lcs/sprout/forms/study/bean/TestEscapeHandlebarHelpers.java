package edu.harvard.mgh.lcs.sprout.forms.study.bean;

import edu.harvard.mgh.lcs.sprout.forms.study.util.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

public class TestEscapeHandlebarHelpers {

	@Test
	public void test() {


		String t = template.replaceAll("(?s)(\\{\\{\\#.*?}})", "<skip>$1</skip>");
		t = template.replaceAll("(?s)(\\{\\{\\#.*?}})", "<skip>$1</skip>");
		t = t.replaceAll("\\{\\{else}}", "<skip>{{else}}</skip>");
//		t = t.replaceAll("</skip>constipation<skip>", "<skip>{{else}}</skip>");


		System.out.println("TestEscapeHandlebarHelpers.test.t: " + t);
	}

	private String template = "some text{{#compare pcori_dsp_interventi.constipation.value \"==\" \"1\"}}constipation{{#if (or\n" +
			"                (eq pcori_dsp_interventi.diarrhea.value \"1\")\n" +
			"                (eq pcori_dsp_interventi.nausea.value \"1\")\n" +
			"                (eq pcori_dsp_interventi.vomiting.value \"1\")\n" +
			"                (eq pcori_dsp_interventi.stool.value \"1\")\n" +
			"                (eq pcori_dsp_interventi.incontinence_stool.value \"1\")\n" +
			"                (eq pcori_dsp_interventi.flatulence.value \"1\")\n" +
			"                (eq pcori_dsp_interventi.bloating.value \"1\")\n" +
			"                (eq pcori_dsp_interventi.ftt.value \"1\")\n" +
			"                )}}, {{else}}{{#if (or\n" +
			"                (eq pcori_dsp_interventi.hyperactivity_adhd.value \"1\")\n" +
			"                (eq pcori_dsp_interventi.inattention.value \"1\")\n" +
			"                (eq pcori_dsp_interventi.impulsivity.value \"1\")\n" +
			"                (eq pcori_dsp_interventi.distracted.value \"1\")\n" +
			"                (eq pcori_dsp_interventi.uncooperative.value \"1\")\n" +
			"                (eq pcori_dsp_interventi.temper_meltdowns_irritability.value \"1\")\n" +
			"                (eq pcori_dsp_interventi.hits_self.value \"1\")\n" +
			"                (eq pcori_dsp_interventi.bites_self.value \"1\")\n" +
			"                (eq pcori_dsp_interventi.injures_self.value \"1\")\n" +
			"                (eq pcori_dsp_interventi.bangs_head.value \"1\")\n" +
			"                (eq pcori_dsp_interventi.irritable.value \"1\")\n" +
			"                (eq pcori_dsp_interventi.hits_others.value \"1\")\n" +
			"                (eq pcori_dsp_interventi.mood_swings.value \"1\")\n" +
			"                (eq pcori_dsp_interventi.aggression.value \"1\")\n" +
			"                (eq pcori_dsp_interventi.property_destruction.value \"1\")\n" +
			"                (eq pcori_dsp_interventi.cries_easily.value \"1\")\n" +
			"                (eq pcori_dsp_interventi.lose_skills.value \"1\")\n" +
			"                )}}";

}
