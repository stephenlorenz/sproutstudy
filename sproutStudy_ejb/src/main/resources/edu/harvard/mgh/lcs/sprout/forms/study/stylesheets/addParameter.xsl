<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0" >

    <xsl:param name="name" />
    <xsl:param name="value" />

    <xsl:variable name="lowercase" select="'abcdefghijklmnopqrstuvwxyz'" />
    <xsl:variable name="uppercase" select="'ABCDEFGHIJKLMNOPQRSTUVWXYZ'" />

    <xsl:output method="xml" encoding="utf-8" omit-xml-declaration="yes" />
    <xsl:template match="@*|node()">
        <xsl:copy>
            <xsl:apply-templates select="@*|node()" />
        </xsl:copy>
    </xsl:template>
    <xsl:template match="/submission/meta/instance">
        <xsl:copy>
            <xsl:if test="not((parameter/@name = translate($name, $lowercase, $uppercase)) or (parameter/@name = translate($name, $uppercase, $lowercase)))">
                <xsl:text>&#xa;            </xsl:text>
                <xsl:element name="parameter" >
                    <xsl:attribute name="name"><xsl:value-of select="$name" /></xsl:attribute>
                    <xsl:value-of select="$value" />
                </xsl:element>
            </xsl:if>
            <xsl:apply-templates/>
        </xsl:copy>
    </xsl:template>
</xsl:stylesheet>