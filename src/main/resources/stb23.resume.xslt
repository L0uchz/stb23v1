<?xml version="1.0"?>

<xsl:stylesheet version="3.0" xmlns:stb="http://univrouen.fr/stb23" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >

    <xsl:output method="html" indent="yes" encoding="UTF-8"/>
    <xsl:template name="main" match="/">
        <xsl:element name="html">
            <xsl:element name="head">
                <xsl:element name="title">STB 23</xsl:element>
            </xsl:element>
            <xsl:element name="body">
                <xsl:element name="h1">STB23 - XSLT V1.0</xsl:element>
                <xsl:element name="p">
                    <xsl:text>N'sonda Charles(</xsl:text>
                    <xsl:value-of select="format-dateTime(current-dateTime(), '[D] [MNn] [Y0001]')"/>
                    <xsl:text>)</xsl:text>
                </xsl:element>

                <xsl:element name="h2"><xsl:value-of select="stb:stb/title"/></xsl:element>
                <xsl:element name="p">(V
                    <xsl:value-of select="stb:stb/version"/> -
                    <xsl:value-of select="stb:stb/date"/>
                    )
                </xsl:element>
                <xsl:element name="p"><xsl:value-of select="stb:stb/description"/></xsl:element>

                <xsl:call-template name="client"/>
                <xsl:call-template name="team"/>
                <xsl:call-template name="features"/>
                <xsl:call-template name="statistics" />

            </xsl:element>
        </xsl:element>
    </xsl:template>

    <xsl:template name="client">
        <xsl:element name="h2">Client : <xsl:value-of select="stb:stb/client/entity"/></xsl:element>
        <xsl:element name="p">
            Interlocuteur : <xsl:value-of select="stb:stb/client/person"/>
            Tel(s) :
            <xsl:for-each select="stb:stb/client/tel">
                <xsl:value-of select="."/>
                <xsl:text> </xsl:text>
            </xsl:for-each>
            Mail(s) :
            <xsl:for-each select="stb:stb/client/mail">
                <xsl:value-of select="."/>
                <xsl:text> </xsl:text>
            </xsl:for-each>
        </xsl:element>
    </xsl:template>

    <xsl:template name="team">
        <xsl:element name="h2">Equipe :</xsl:element>
        <xsl:element name="ul">
            <xsl:for-each select="stb:stb/team/member">
                <xsl:element name="li">
                    <xsl:value-of select="person/@gender"/>
                    <xsl:text> </xsl:text>
                    <xsl:value-of select="person"/>
                    <xsl:text> </xsl:text>
                    <xsl:value-of select="person/@lastname"/>
                    <xsl:text>, </xsl:text>
                    <xsl:value-of select="function"/>
                    <xsl:text> (</xsl:text>
                    <xsl:value-of select="mail"/>
                    <xsl:text>)</xsl:text>
                </xsl:element>
            </xsl:for-each>
        </xsl:element>
    </xsl:template>

    <xsl:template name="features">
        <xsl:element name="h2">Liste des fonctionnalités :</xsl:element>

        <xsl:element name="ol">
            <xsl:for-each select="stb:stb/features/feature">
                <xsl:element name="li">
                    <xsl:value-of select="@number" />
                    <xsl:text>-</xsl:text>
                    <xsl:value-of select="@section" />
                    <xsl:text> : </xsl:text>
                    <xsl:value-of select="@name" />
                </xsl:element>
            </xsl:for-each>
        </xsl:element>

        <xsl:element name="table">
            <xsl:for-each select="stb:stb/features/feature">
                <xsl:element name="tr">
                    <xsl:element name="td">item=<xsl:value-of select="@number"/><xsl:text>.</xsl:text><xsl:value-of select="@section"/></xsl:element>
                    <xsl:element name="td"><xsl:value-of select="@name"/></xsl:element>
                </xsl:element>

                <xsl:element name="tr">
                    <xsl:element name="td">priorité : <xsl:value-of select="priority"/></xsl:element>
                    <xsl:element name="td">Livraison : <xsl:value-of select="delivery"/></xsl:element>
                </xsl:element>

                <xsl:element name="tr">
                    <xsl:element name="td">
                        <xsl:value-of select="description"/>
                        <xsl:element name="br"/>
                        <xsl:value-of select="comment"/>
                    </xsl:element>
                </xsl:element>

                <xsl:element name="tr">
                    <xsl:element name="td">
                        <xsl:element name="br"/>
                    </xsl:element>
                </xsl:element>

            </xsl:for-each>
        </xsl:element>
    </xsl:template>

    <xsl:template name="statistics">
        <xsl:element name="h2">Statistiques</xsl:element>

        <xsl:element name="p">
            <xsl:text>Nb fonctionnalités = </xsl:text>
            <xsl:value-of select="count(stb:stb/features/feature)" />
        </xsl:element>

        <xsl:element name="ol">
            <xsl:for-each-group select="stb:stb/features/feature" group-by="priority">

                <xsl:element name="li">
                    <xsl:text>priorité = </xsl:text>
                    <xsl:value-of select="priority" />
                    <xsl:text> : </xsl:text>
                    <xsl:value-of select="count(current-group())" />
                    <xsl:text> fonctionnalité</xsl:text>
                    <xsl:choose>
                        <xsl:when test="count(current-group()) &gt; 1">
                            <xsl:text>s</xsl:text>
                        </xsl:when>
                    </xsl:choose>
                </xsl:element>

            </xsl:for-each-group>
        </xsl:element>
    </xsl:template>

</xsl:stylesheet>