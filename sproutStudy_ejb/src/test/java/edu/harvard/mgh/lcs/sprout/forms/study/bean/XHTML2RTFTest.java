package edu.harvard.mgh.lcs.sprout.forms.study.bean;

import edu.harvard.mgh.lcs.sprout.forms.study.beaninterface.SproutTransformService;
import edu.harvard.mgh.lcs.sprout.forms.study.util.StringUtils;
import edu.harvard.mgh.lcs.sprout.forms.transform.xalan.extension.XHTML2RTF;
import org.junit.Test;

import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;

public class XHTML2RTFTest {

	@Test
	public void test() {

		XHTML2RTF xhtml2RTF = new XHTML2RTF();

		System.out.println("XHTML2RTFTest.test");

		String narrative = "<html xmlns=\"http://www.w3.org/1999/xhtml\">\n" +
				"<head>\n" +
				"  <meta name=\"generator\" content=\"HTML Tidy for Mac OS X (vers 31 October 2006 - Apple Inc. build 15.15), see www.w3.org\" />\n" +
				"\n" +
				"  <title></title>\n" +
				"</head>\n" +
				"\n" +
				"<body>\n" +
				"  <h3>Clinical NOTE:</h3>\n" +
				"\n" +
				"<div>\n" +
				"Jared Moon, MD was the technician for this study. This study was of the type, Test A. The cardiologist was Amy Spooner, MD and the radiologist was Alan J Fischman, MD.\n" +
				"</div>\n" +
				"\n" +
				"  <ul>\n" +
				"    <li>Cat</li>\n" +
				"  </ul>\n" +
				"\n<div><table><tr><td>ID</td><td>Name</td></tr><tr><td>Bob</td><td>Dole</td></tr><tr><td>Fred</td><td>Rogers</td></tr></table></div>" +
				"<p>This is another note.</p>\n" +
				"</body>\n" +
				"</html>";
//		String narrative = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
//				"<Class>\n" +
//				"<Order Name=\"TINAMIFORMES\">\n" +
//				"        <Family Name=\"TINAMIDAE\">\n" +
//				"            <Species Scientific_Name=\"Tinamus major\">  Great Tinamou.</Species>\n" +
//				"            <Species Scientific_Name=\"Nothocercus\">Highland Tinamou.</Species>\n" +
//				"            <Species Scientific_Name=\"Crypturellus soui\">Little Tinamou.</Species>\n" +
//				"            <Species Scientific_Name=\"Crypturellus cinnamomeus\">Thicket Tinamou.</Species>\n" +
//				"            <Species Scientific_Name=\"Crypturellus boucardi\">Slaty-breasted Tinamou.</Species>\n" +
//				"            <Species Scientific_Name=\"Crypturellus kerriae\">Choco Tinamou.</Species>\n" +
//				"        </Family>\n" +
//				"    </Order>\n" +
//				"<Order Name=\"GAVIIFORMES\">\n" +
//				"        <Family Name=\"GAVIIDAE\">\n" +
//				"            <Species Scientific_Name=\"Gavia stellata\">Red-throated Loon.</Species>\n" +
//				"            <Species Scientific_Name=\"Gavia arctica\">Arctic Loon.</Species>\n" +
//				"            <Species Scientific_Name=\"Gavia pacifica\">Pacific Loon.</Species>\n" +
//				"            <Species Scientific_Name=\"Gavia immer\">Common Loon.</Species>\n" +
//				"            <Species Scientific_Name=\"Gavia adamsii\">Yellow-billed Loon.</Species>\n" +
//				"        </Family>\n" +
//				"    </Order>\n" +
//				"\n" +
//				"\n" +
//				"<Order Name=\"PODICIPEDIFORMES\">\n" +
//				"        <Family Name=\"PODICIPEDIDAE\">\n" +
//				"            <Species Scientific_Name=\"Tachybaptus dominicus\">Least Grebe.</Species>\n" +
//				"            <Species Scientific_Name=\"Podilymbus podiceps\">Pied-billed Grebe.</Species>\n" +
//				"            <Species Scientific_Name=\"\">Atitlan Grebe.</Species>\n" +
//				"            <Species Scientific_Name=\"\">Horned Grebe.</Species>\n" +
//				"            <Species Scientific_Name=\"\">Red-necked Grebe.</Species>\n" +
//				"            <Species Scientific_Name=\"\">Eared Grebe.</Species>\n" +
//				"            <Species Scientific_Name=\"\">Western Grebe.</Species>\n" +
//				"            <Species Scientific_Name=\"\">Clark's Grebe.</Species>\n" +
//				"            <Species Scientific_Name=\"\"/>\n" +
//				"        </Family>\n" +
//				"    </Order>\n" +
//				"\n" +
//				"\n" +
//				"<Order Name=\"PROCELLARIIFORMES\">\n" +
//				"        <Family Name=\"DIOMEDEIDAE\">\n" +
//				"            <Species Scientific_Name=\"Thalassarche chlororhynchos\">Yellow-nosed Albatross. (A)</Species>\n" +
//				"            <Species Scientific_Name=\"Thalassarche cauta\">Shy Albatross. (A)</Species>\n" +
//				"            <Species Scientific_Name=\"Thalassarche melanophris\">Black-browed Albatross. (A)</Species>\n" +
//				"            <Species Scientific_Name=\"Phoebetria palpebrata\">Light-mantled Albatross. (A)</Species>\n" +
//				"            <Species Scientific_Name=\"Diomedea exulans\">Wandering Albatross. (A)</Species>\n" +
//				"            <Species Scientific_Name=\"Phoebastria immutabilis\">Laysan Albatross.</Species>\n" +
//				"            <Species Scientific_Name=\"Phoebastria nigripes\">Black-footed Albatross.</Species>\n" +
//				"            <Species Scientific_Name=\"Phoebastria albatrus\">Short-tailed Albatross. (N)</Species>\n" +
//				"        </Family>\n" +
//				"        <Family Name=\"PROCELLARIIDAE\">\n" +
//				"            <Species Scientific_Name=\"Fulmarus glacialis\">Northern Fulmar.</Species>\n" +
//				"            <Species Scientific_Name=\"Pterodroma neglecta\">Kermadec Petrel. (A)</Species>\n" +
//				"            <Species Scientific_Name=\"Pterodroma arminjoniana\">Herald Petrel. (A)</Species>\n" +
//				"            <Species Scientific_Name=\"Pterodroma ultima\">Murphy's Petrel. (N)</Species>\n" +
//				"            <Species Scientific_Name=\"Pterodroma inexpectata\">Mottled Petrel. (A)</Species>\n" +
//				"            <Species Scientific_Name=\"Pterodroma cahow\">Bermuda Petrel.</Species>\n" +
//				"            <Species Scientific_Name=\"Pterodroma hasitata\">Black-capped Petrel.</Species>\n" +
//				"            <Species Scientific_Name=\"Pterodroma externa\">Juan Fernandez Petrel. (N)</Species>\n" +
//				"            <Species Scientific_Name=\"Pterodroma phaeopygia\">Dark-rumped Petrel.</Species>\n" +
//				"            <Species Scientific_Name=\"Pterodroma cervicalis\">White-necked Petrel. (H)</Species>\n" +
//				"            <Species Scientific_Name=\"Pterodroma hypoleuca\">Bonin Petrel. (H)</Species>\n" +
//				"            <Species Scientific_Name=\"Pterodroma nigripennis\">Black-winged Petrel. (H, A)</Species>\n" +
//				"            <Species Scientific_Name=\"Pterodroma cookii\">Cook's Petrel. (N)</Species>\n" +
//				"            <Species Scientific_Name=\"Pterodroma longirostris\">Stejneger's Petrel. (A)</Species>\n" +
//				"            <Species Scientific_Name=\"Bulweria bulwerii\">Bulwer's Petrel. (H)</Species>\n" +
//				"            <Species Scientific_Name=\"Bulweria fallax\">Jouanin's Petrel. (H, A)</Species>\n" +
//				"            <Species Scientific_Name=\"Procellaria parkinsoni\">Parkinson's Petrel. (N)</Species>\n" +
//				"            <Species Scientific_Name=\"Calonectris leucomelas\">Streaked Shearwater. (A)</Species>\n" +
//				"            <Species Scientific_Name=\"Calonectris diomedea\">Cory's Shearwater. (N)</Species>\n" +
//				"            <Species Scientific_Name=\"Puffinus creatopus\">Pink-footed Shearwater. (N)</Species>\n" +
//				"            <Species Scientific_Name=\"Puffinus carneipes\">Flesh-footed Shearwater. (N)</Species>\n" +
//				"            <Species Scientific_Name=\"Puffinus gravis\">Greater Shearwater. (N)</Species>\n" +
//				"            <Species Scientific_Name=\"Puffinus pacificus\">Wedge-tailed Shearwater.</Species>\n" +
//				"            <Species Scientific_Name=\"Puffinus bulleri\">Buller's Shearwater. (N)</Species>\n" +
//				"            <Species Scientific_Name=\"Puffinus griseus\">Sooty Shearwater. (N)</Species>\n" +
//				"            <Species Scientific_Name=\"Puffinus tenuirostris\">Short-tailed Shearwater. (N)</Species>\n" +
//				"            <Species Scientific_Name=\"Puffinus nativitatis\">Christmas Shearwater. (H)</Species>\n" +
//				"            <Species Scientific_Name=\"Puffinus puffinus\">Manx Shearwater.</Species>\n" +
//				"            <Species Scientific_Name=\"Puffinus auricularis\">Townsend's Shearwater.</Species>\n" +
//				"            <Species Scientific_Name=\"Puffinus opisthomelas\">Black-vented Shearwater.</Species>\n" +
//				"            <Species Scientific_Name=\"Puffinus lherminieri\">Audubon's Shearwater.</Species>\n" +
//				"            <Species Scientific_Name=\"Puffinus assimilis\">Little Shearwater. (A)</Species>\n" +
//				"        </Family>\n" +
//				"        <Family Name=\"HYDROBATIDAE\">\n" +
//				"            <Species Scientific_Name=\"Oceanites oceanicus\">Wilson's Storm-Petrel. (N)</Species>\n" +
//				"            <Species Scientific_Name=\"Pelagodroma marina\">White-faced Storm-Petrel. (A)</Species>\n" +
//				"            <Species Scientific_Name=\"Hydrobates pelagicus\">European Storm-Petrel. (A)</Species>\n" +
//				"            <Species Scientific_Name=\"Oceanodroma furcata\">Fork-tailed Storm-Petrel.</Species>\n" +
//				"            <Species Scientific_Name=\"Oceanodroma leucorhoa\">Leach's Storm-Petrel.</Species>\n" +
//				"            <Species Scientific_Name=\"Oceanodroma homochroa\">Ashy Storm-Petrel.</Species>\n" +
//				"            <Species Scientific_Name=\"Oceanodroma castro\">Band-rumped Storm-Petrel. (N)</Species>\n" +
//				"            <Species Scientific_Name=\"Oceanodroma tethys\">Wedge-rumped Storm-Petrel. (N)</Species>\n" +
//				"            <Species Scientific_Name=\"Oceanodroma melania\">Black Storm-Petrel.</Species>\n" +
//				"            <Species Scientific_Name=\"Oceanodroma macrodactyla\">Guadalupe Storm-Petrel.</Species>\n" +
//				"            <Species Scientific_Name=\"Oceanodroma markhami\">Markham's Storm-Petrel. (A)</Species>\n" +
//				"            <Species Scientific_Name=\"Oceanodroma tristrami\">Tristram's Storm-Petrel. (H)</Species>\n" +
//				"            <Species Scientific_Name=\"Oceanodroma microsoma\">Least Storm-Petrel.</Species>\n" +
//				"        </Family>\n" +
//				"    </Order>\n" +
//				"\n" +
//				"<Order Name=\"PELECANIFORMES\">\n" +
//				"        <Family Name=\"PHAETHONTIDAE\">\n" +
//				"            <Species Scientific_Name=\"Phaethon lepturus\">White-tailed Tropicbird.</Species>\n" +
//				"            <Species Scientific_Name=\"Phaethon aethereus\">Red-billed Tropicbird.</Species>\n" +
//				"            <Species Scientific_Name=\"Phaethon rubricauda\">Red-tailed Tropicbird.</Species>\n" +
//				"        </Family>\n" +
//				"        <Family Name=\"SULIDAE\">\n" +
//				"            <Species Scientific_Name=\"Sula dactylatra\">Masked Booby.</Species>\n" +
//				"            <Species Scientific_Name=\"Sula nebouxii\">Blue-footed Booby.</Species>\n" +
//				"            <Species Scientific_Name=\"Sula variegata\">Peruvian Booby. (A)</Species>\n" +
//				"            <Species Scientific_Name=\"Sula leucogaster\">Brown Booby.</Species>\n" +
//				"            <Species Scientific_Name=\"Sula sula\">Red-footed Booby.</Species>\n" +
//				"            <Species Scientific_Name=\"Morus bassanus\">Northern Gannet.</Species>\n" +
//				"        </Family>\n" +
//				"        <Family Name=\"PELECANIDAE\">\n" +
//				"            <Species Scientific_Name=\"Pelecanus erythrorhynchos\">American White Pelican.</Species>\n" +
//				"            <Species Scientific_Name=\"Pelecanus occidentalis\">Brown Pelican.</Species>\n" +
//				"        </Family>\n" +
//				"        <Family Name=\"PHALACROCORACIDAE\">\n" +
//				"            <Species Scientific_Name=\"Phalacrocorax penicillatus\">Brandt's Cormorant.</Species>\n" +
//				"            <Species Scientific_Name=\"Phalacrocorax brasilianus\">Neotropic Cormorant.</Species>\n" +
//				"            <Species Scientific_Name=\"Phalacrocorax auritus\">Double-crested Cormorant.</Species>\n" +
//				"            <Species Scientific_Name=\"Phalacrocorax carbo\">Great Cormorant.</Species>\n" +
//				"            <Species Scientific_Name=\"Phalacrocorax urile\">Red-faced Cormorant.</Species>\n" +
//				"            <Species Scientific_Name=\"Phalacrocorax pelagicus\">Pelagic Cormorant.</Species>\n" +
//				"        </Family>\n" +
//				"        <Family Name=\"ANHINGIDAE\">\n" +
//				"            <Species Scientific_Name=\"Anhinga anhinga\">Anhinga.</Species>\n" +
//				"        </Family>\n" +
//				"        <Family Name=\"FREGATIDAE\">\n" +
//				"            <Species Scientific_Name=\"Fregata magnificens\">Magnificent Frigatebird.</Species>\n" +
//				"            <Species Scientific_Name=\"Fregata minor\">Great Frigatebird.</Species>\n" +
//				"            <Species Scientific_Name=\"Fregata ariel\">Lesser Frigatebird. (A)</Species>\n" +
//				"        </Family>\n" +
//				"    </Order>\n" +
//				"</Class>";

		if (StringUtils.isFull(narrative)) {


//			try {
//				TransformerFactory tFactory = TransformerFactory.newInstance();
//
//
//				InputStream stream = SproutTransformService.class.getClassLoader().getResourceAsStream("edu/harvard/mgh/lcs/sprout/forms/study/stylesheets/xhtml2rtf.xsl");
////				InputStream stream = SproutTransformService.class.getClassLoader().getResourceAsStream("edu/harvard/mgh/lcs/sprout/forms/study/stylesheets/birds.xsl");
//
//				// Use the TransformerFactory to instantiate a Transformer that will work with
//				// the stylesheet you specify. This method call also processes the stylesheet
//				// into a compiled Templates object.
////				Transformer transformer = tFactory.newTransformer(new StreamSource(stream));
//
//
//				Templates xsl = tFactory.newTemplates(new StreamSource(stream));
//
//
//				StringWriter writer = new StringWriter();
//
//				// Use the Transformer to apply the associated Templates object to an XML document
//				// (foo.xml) and write the output to a file (foo.out).
//				Transformer transformer = xsl.newTransformer();
//				transformer.transform(new StreamSource(new StringReader(narrative)), new StreamResult(writer));
//
//				String rtf = writer.toString();
//
//			} catch (Exception e) {
//				e.printStackTrace();
//			}


			try {
				TransformerFactory factory = TransformerFactory.newInstance();
				InputStream stream = SproutTransformService.class.getClassLoader().getResourceAsStream("edu/harvard/mgh/lcs/sprout/forms/study/stylesheets/xhtml2rtf.xsl");
//				InputStream stream = SproutTransformService.class.getClassLoader().getResourceAsStream("edu/harvard/mgh/lcs/sprout/forms/study/stylesheets/birds.xsl");

//				Templates templates = factory.newTemplates(new StreamSource(stream));


				Transformer transformer = factory.newTransformer(new javax.xml.transform.stream.StreamSource(stream));
				transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
				transformer.setOutputProperty(OutputKeys.INDENT, "yes");
//				Transformer transformer = templates.newTransformer();
				StreamSource streamSource = new StreamSource(new StringReader(narrative));
				StringWriter writer = new StringWriter();
				transformer.transform(streamSource, new javax.xml.transform.stream.StreamResult(writer));
				String rtf = writer.toString();
				System.out.println("rtf = " + rtf);
			} catch (TransformerConfigurationException e) {
				e.printStackTrace();
			} catch (TransformerException e) {
				e.printStackTrace();
			}
		}



	}

}
