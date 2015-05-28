package edu.harvard.mgh.lcs.sprout.forms.transform.xalan.extension;

public class XHTML2RTF {

    private int multiplyWidth = 192;
    private int marginWidth = 196;
    private int arrMaxLen;
    private int arrTotLen;

//    public static void init(org.apache.xalan.extensions.XSLProcessorContext context, org.w3c.dom.Element elem) {
//
//        System.out.println("XHTML2RTF.init");
//        System.out.println("context = [" + context + "], elem = [" + elem + "]");
//
//        String name = elem.getAttribute("name");
//        String value = elem.getAttribute("value");
//        int val;
//        try {
//            val = Integer.parseInt(value);
//        } catch (NumberFormatException e) {
//            e.printStackTrace();
//            val = 0;
//        }
//    }

    public static String charCode(String strText) {

        System.out.println("XHTML2RTF.charCode");
        System.out.println("strText = [" + strText + "]");

        String strCharCodes = "###";
        String strSeparator = "";
//        for (var intChar = 0; intChar &lt; strText.length; intChar++)
//        {
//            strCharCodes += strText.charCodeAt(intChar).toString() + strSeparator;
//            strSeparator = ","
//        }
        strCharCodes += "###";
        return(strCharCodes);
    }

    public static String rtfEncode(Object objXMLNodes, String strText, int intMyNormalizeSpaces) {

        System.out.println("XHTML2RTF.RTFEncode");
        System.out.println("objXMLNodes = [" + objXMLNodes + "], strText = [" + strText + "], intMyNormalizeSpaces = [" + intMyNormalizeSpaces + "]");

        return "RTFEncoded...";

        // Encode text, character by character

//        if (intMyNormalizeSpaces == 1)
//        {
//            // Replace multiple spaces by one single space
//            strText = strText.replace(/ +/g, " ");
//        }
//
//        var blnAppendParagraphBreak = false;
//
//        // Build an array of characters
//        var arrChars = strText.split("");
//        for (var intChar = 0; intChar &lt; arrChars.length; intChar++)
//        {
//            var strChar = arrChars[intChar];
//            switch (strChar.valueOf())
//            {
//                case "\\":
//                case "{":
//                case "}":
//                    // Encode backslashes, left curly bracket, right curly bracket (prefix with a backslash)
//                    arrChars[intChar] = "\\" + strChar;
//                    break;
//
//                case "&#160;":
//                    // Encode non-breacking space (backslash+tilda)
//                    arrChars[intChar] = "\\~";
//                    break;
//
//                case "\n":
//                    if (intMyNormalizeSpaces == 2)
//                    {
//                        // Preformatted mode - use \line for all EOL characters
//                        arrChars[intChar] = "\\line ";
//                        // Check if next node is a paragraph - if yes, we will use a paragraph break INSTEAD of line break
//                        if (objXMLNodes != null &amp;&amp; objXMLNodes.length != 0)
//                        {
//                            var objXMLContext = objXMLNodes[0];
//                            var objNextNode = objXMLContext.selectSingleNode("following-sibling::node()[position() = 1]");
//                            if (objNextNode != null)
//                            {
//                                if (objNextNode.nodeName == "p")
//                                {
//                                    blnAppendParagraphBreak = true;
//                                }
//                            }
//                        }
//                    }
//                    break;
//
//                default:
//                    var intCharCode = strChar.charCodeAt(0);
//                    if (intCharCode &gt; 255)
//                {
//                    arrChars[intChar] = "\\u" + intCharCode.toString() + "  ";
//                }
//                else
//                {
//                    // TODO Handle control characters (ASCII code lesser than 32 - TAB, EOL, etc...)
//                    // No encoding
//                }
//                break;
//
//            }
//        }
//
//        // Convert back array to string
//        var strRTFEncoded = arrChars.join("");
//
//        if (blnAppendParagraphBreak)
//        {
//            // Append a paragraph break - next node is a p tag, but we are not inside a p tag (bad!)
//            strRTFEncoded += "\\par ";
//        }
//
//        return(strRTFEncoded);
    }

    public static String tableCellWidthFill(Object objXMLNodes, int tableWidth) {

        System.out.println("XHTML2RTF.TableCellWidthFill");
        System.out.println("objXMLNodes = [" + objXMLNodes + "], tableWidth = [" + tableWidth + "]");

        return "TableCellWidthFill...";

//        var strText = "";
//        if (objXMLNodes != null &amp;&amp; objXMLNodes.length != 0)
//        {
//            var objRowNodes = objXMLNodes[0].selectNodes("./*");
//            var objRowNode;
//            var objColNodes;
//            arrMaxLen = new Array();
//            arrTotLen = new Array();
//            var maxWordLen;
//            for(var i = 0; i &lt; objRowNodes.length; i++ )
//            {
//                objRowNode = objRowNodes[i];
//                objColNodes = objRowNode.selectNodes("./*");
//                for(var j = 0; j &lt; objColNodes.length; j++ )
//                {
//                    var arrWords = objColNodes[j].text.split(/[\s+]/);
//                    maxWordLen = 0;
//                    arrTotLen[j] = objColNodes[j].text.length;
//                    for(var iWord = 0; iWord &lt; arrWords.length; iWord++)
//                    {
//                        if(arrWords[iWord].length > maxWordLen )
//                        {
//                            maxWordLen = arrWords[iWord].length;
//                        }
//                    }
//                    arrMaxLen[j] = arrMaxLen[j] &gt; maxWordLen ? arrMaxLen[j] : (maxWordLen+1);
//                }
//            }
//            if( tableWidth &gt; 0 )
//            {
//                var totalWidthTot = 0;
//                var totalWidthMax = 0;
//
//                for(var i = 0; i &lt; arrMaxLen.length; i++ )
//                {
//                    totalWidthTot += (arrTotLen[i]*multiplyWidth + 2*marginWidth);
//                    totalWidthMax += (arrMaxLen[i]*multiplyWidth + 2*marginWidth);
//                }
//
//                var tableWidthTot = tableWidth;
//                var tableWidthMax = tableWidth;
//                var midWidthTot = tableWidth / arrTotLen.length;
//                var midWidthMax = tableWidth / arrMaxLen.length;
//                var midAddTot = (tableWidth - totalWidthTot) / arrTotLen.length;
//                var midAddMax = (tableWidth - totalWidthMax) / arrMaxLen.length;
//
//                //strText += "totalWidthTot = " + totalWidthTot.toString() + " \n";
//                //strText += "totalWidthMax = " + totalWidthMax.toString() + " \n";
//                //strText += "midAddTot = " + midAddTot.toString() + " \n";
//                //strText += "midAddMax = " + midAddMax.toString() + " \n";
//                for(var i = 0; i &lt; arrMaxLen.length; i++ )
//                {
//                    arrMaxLen[i] = (arrMaxLen[i]*multiplyWidth + 2*marginWidth)*tableWidth/totalWidthTot/multiplyWidth;
//                }
//            }
//        }
//        return(strText);
    }

    public static String getTableColumnWidth(int iColumn, int bSum, int font_size) {

        System.out.println("XHTML2RTF.GetTableColumnWidth");
        System.out.println("iColumn = [" + iColumn + "], bSum = [" + bSum + "], font_size = [" + font_size + "]");

        return "GetTableColumnWidth...";

//        multiplyWidth = font_size*8;
//        var sum = 0;
//        iColumn = iColumn-1;
//        if( iColumn &lt; arrMaxLen.length &amp;&amp; iColumn &gt;= 0 )
//        {
//            if( bSum == 0 )
//            {
//                return(2*marginWidth +  arrMaxLen[iColumn]*multiplyWidth);
//            }
//            else if( bSum == 1 )
//            {
//                return(2*marginWidth +  arrTotLen[iColumn]*multiplyWidth);
//            }
//            else if( bSum == 2 )
//            {
//                for(var i = 0; i &lt;= iColumn; i++ )
//                {
//                    sum += (2*marginWidth + arrMaxLen[i]*multiplyWidth);
//                }
//                return(sum);
//            }
//            else if( bSum == 3 )
//            {
//                for(var i = 0; i &lt;= iColumn; i++ )
//                {
//                    sum += (2*marginWidth + arrTotLen[i]*multiplyWidth);
//                }
//                return(sum);
//            }
//        }
//        return 0;
    }

}
