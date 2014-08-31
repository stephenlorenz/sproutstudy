<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0" >
    
    <xsl:param name="schema" />
    <xsl:param name="identity" />
    
    <xsl:variable name="lowercase" select="'abcdefghijklmnopqrstuvwxyz'" />
    <xsl:variable name="uppercase" select="'ABCDEFGHIJKLMNOPQRSTUVWXYZ'" />
    
    <xsl:output method="xml" encoding="utf-8" omit-xml-declaration="yes" />
    <xsl:template match="@*|node()">
        <xsl:copy>
            <xsl:apply-templates select="@*|node()" />
        </xsl:copy>
    </xsl:template>
    <xsl:template match="/submission/meta/user/identities">
        <xsl:copy>
            <xsl:if test="not((identity/@schema = translate($schema, $lowercase, $uppercase)) or (identity/@schema = translate($schema, $uppercase, $lowercase)))">
                <xsl:text>&#xa;                </xsl:text>
                <xsl:element name="identity" >
                    <xsl:attribute name="schema"><xsl:value-of select="$schema" /></xsl:attribute>
                    <xsl:attribute name="scope">domain</xsl:attribute>
                    <xsl:value-of select="$identity" />
                </xsl:element>
            </xsl:if>
            <xsl:apply-templates/>
        </xsl:copy>
    </xsl:template>
</xsl:stylesheet>