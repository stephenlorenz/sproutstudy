<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:param name="fieldCode" />
    <xsl:param name="fieldValue" />
    
    <xsl:output method="xml" encoding="utf-8" omit-xml-declaration="yes"/>
    <xsl:template match="@*|node()">
        <xsl:copy>
            <xsl:apply-templates select="@*|node()"/>
        </xsl:copy>
    </xsl:template>
    
    <xsl:template match="/submission/meta/user/identities/assertions/assertion/*">
        <xsl:choose>
            <xsl:when test="(preceding-sibling::name = $fieldCode or following-sibling::name = $fieldCode) and name() = 'value'">
                <xsl:copy>
                    <xsl:value-of select="$fieldValue" />
                </xsl:copy>
            </xsl:when>
            <xsl:otherwise>
                <xsl:copy>
                    <xsl:apply-templates select="@* | node()"/>
                </xsl:copy>
            </xsl:otherwise>
        </xsl:choose>
    </xsl:template>
    

</xsl:stylesheet>
