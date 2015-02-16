<?xml version="1.0"?>

<!-- Version: $Id: xmlspec.xsl,v 1.7 2001/02/12 17:02:20 ndw Exp $ -->

<!-- Stylesheet for XMLspec -->
<!-- Author: Norman Walsh (Norman.Walsh@East.Sun.COM) -->
<!-- Author: Chris Maden (crism@lexica.net) -->
<!-- Author: Ben Trafford (ben@legendary.org) -->
<!-- Author: Eve Maler (eve.maler@east.sun.com) -->
<!-- Date Created: 1999.09.07 -->

<!-- This stylesheet is copyright (c) 2000 by its authors.  Free
     distribution and modification is permitted, including adding to
     the list of authors and copyright holders, as long as this
     copyright notice is maintained. -->

<!-- This stylesheet attempts to implement the XML Specification V2.1
     DTD.  Documents conforming to earlier DTDs may not be correctly
     transformed. -->

<!-- ChangeLog:

     25 Sep 2000: (Norman.Walsh@East.Sun.COM)
       - Sync'd with Eve's version:
         o Concatenated each inline element's output all on one line
           to avoid spurious spaces in the output. (This is really an
           IE bug, but...) (15 Sep 2000)
         o Updated crism's email address in header (7 Sep 2000)
         o Changed handling of affiliation to use comma instead of
           parentheses (9 Aug 2000)

     14 Aug 2000: (Norman.Walsh@East.Sun.COM)

       - Added additional.title param (for diffspec.xsl to change)
       - Fixed URI of W3C home icon
       - Made CSS stylesheet selection depend on the w3c-doctype attribute
         of spec instead of the w3c-doctype element in the header

     26 Jul 2000: (Norman.Walsh@East.Sun.COM)

       - Improved semantics of specref. Added xsl:message for unsupported
         cases. (I'm by no means confident that I've covered the whole
         list.)
       - Support @role on author.
       - Make lhs/rhs "code" in EBNF.
       - Fixed bug in ID/IDREF linking.
       - More effectively disabled special markup for showing @diffed
         versions

     21 Jul 2000: (Norman.Walsh@East.Sun.COM)

       - Added support for @diff change tracking, primarily through
         the auxiliary stylesheet diffspec.xsl. However, it was
         impractical to handle some constructions, such as DLs and TABLEs,
         in a completely out-of-band manner. So there is some inline
         support for @diff markup.

       - Added $additional.css to allow downstream stylesheets to add
         new markup to the <style> element.

       - Added required "type" attribute to the <style> element.

       - Fixed pervasive problem with nested <a> elements.

       - Added doctype-public to xsl:output.

       - Added $validity.hacks. If "1", then additional disable-output-escaping
         markup may be inserted in some places to attempt to get proper,
         valid HTML. For example, if a <glist> appears inside a <p> in the
         xmlspec source, this creates a nested <dl> inside a <p> in the
         HTML, which is not valid. If $validity.hacks is "1", then an
         extra </p>, <p> pair is inserted around the <dl>.
-->

<xsl:transform xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
               xmlns:saxon="http://icl.com/saxon"
               exclude-result-prefixes="saxon"
               version="1.0">

  <xsl:param name="validity.hacks">1</xsl:param>
  <xsl:param name="show.diff.markup">0</xsl:param>
  <xsl:param name="additional.css"></xsl:param>
  <xsl:param name="additional.title"></xsl:param>
  <xsl:param name="called.by.diffspec">0</xsl:param>
  <xsl:param name="show.ednotes">1</xsl:param>

  <xsl:output method="html"
       encoding="ISO-8859-1"
       doctype-public="-//W3C//DTD HTML 4.01 Transitional//EN"
       indent="no"/>

  <xsl:strip-space elements="author"/>

  <!-- not handled:
    attribute:   unhandled IDL stuff
    case:        unhandled IDL stuff
    component:   unhandled IDL stuff
    constant:    unhandled IDL stuff
    copyright:   boilerplate notice always used instead
    definitions: unhandled IDL stuff
    descr:       unhandled IDL stuff
    enum:        unhandled IDL stuff
    enumerator:  unhandled IDL stuff
    exception:   unhandled IDL stuff
    group:       unhandled IDL stuff
    interface:   unhandled IDL stuff
    method:      unhandled IDL stuff
    module:      unhandled IDL stuff
    param:       unhandled IDL stuff
    parameters:  unhandled IDL stuff
    raises:      unhandled IDL stuff
    reference:   unhandled IDL stuff
    returns:     unhandled IDL stuff
    sequence:    unhandled IDL stuff
    struct:      unhandled IDL stuff
    typedef:     unhandled IDL stuff
    typename:    unhandled IDL stuff
    union:       unhandled IDL stuff

    Warning!
    Only handles statuses of NOTE, WD, and REC.
    -->

  <!-- Template for the root node.  Creation of <html> element could
       go here, but that doesn't feel right. -->
  <xsl:template match="/">
    <xsl:apply-templates/>
  </xsl:template>

  <!-- abstract: appears only in header -->
  <!-- format as a second-level div -->
  <!-- called in enforced order from header's template -->
  <xsl:template match="abstract">
    <div>
      <xsl:text>&#10;</xsl:text>
      <h2>
        <a name="abstract">Abstract</a>
      </h2>
      <xsl:apply-templates/>
    </div>
  </xsl:template>

  <!-- affiliation: follows a name in author and member -->
  <!-- put it in parens with a leading space -->
  <xsl:template match="affiliation">
    <xsl:text>, </xsl:text>
    <xsl:apply-templates/>
  </xsl:template>

  <!-- arg: appears only in proto -->
  <!-- argument in function prototype -->
  <!-- output argument type, italicized as placeholder; separate the
       list with commas and spaces -->
  <xsl:template match="arg">
    <xsl:if test="preceding-sibling::arg">
      <xsl:text>, </xsl:text>
    </xsl:if>
    <var>
      <xsl:value-of select="@type"/>
    </var>
    <xsl:if test="@occur = 'opt'">
      <xsl:text>?</xsl:text>
    </xsl:if>
  </xsl:template>

  <!-- body: the meat of the spec -->
  <!-- create a TOC and then go to work -->
  <!-- (don't forget the TOC for the back matter and a pointer to end
       notes) -->
  <xsl:template match="body">
    <div class="toc">
      <xsl:text>&#10;</xsl:text>
      <h2>
        <a name="contents">Table of Contents</a>
      </h2>
      <p class="toc">
        <xsl:apply-templates mode="toc" select="div1"/>
      </p>
      <xsl:if test="../back">
        <xsl:text>&#10;</xsl:text>
        <h3>
          <xsl:text>Appendi</xsl:text>
          <xsl:choose>
            <xsl:when test="count(../back/div1 | ../back/inform-div1) > 1">
              <xsl:text>ces</xsl:text>
            </xsl:when>
            <xsl:otherwise>
              <xsl:text>x</xsl:text>
            </xsl:otherwise>
          </xsl:choose>
        </h3>
        <p class="toc">
          <xsl:apply-templates mode="toc"
            select="../back/div1 | ../back/inform-div1"/>
        </p>
      </xsl:if>
      <xsl:if test="//footnote">
        <p class="toc">
          <a href="#endnotes">
            <xsl:text>End Notes</xsl:text>
          </a>
        </p>
      </xsl:if>
    </div>
    <hr/>
    <div class="body">
      <xsl:apply-templates/>
    </div>
  </xsl:template>

  <!-- caption: see table -->

  <!-- case: -->
  <!-- IDL stuff isn't handled yet -->

  <!-- code: generic computer code -->
  <!-- output as HTML <code> for monospaced formatting -->
  <xsl:template match="code">
    <code><xsl:apply-templates/></code>
  </xsl:template>
  

  <!-- mode: divnum -->
  <xsl:template mode="divnum" match="div1">
    <xsl:number format="1 "/>
  </xsl:template>

  <xsl:template mode="divnum" match="back/div1 | inform-div1">
    <xsl:number count="div1 | inform-div1" format="A "/>
  </xsl:template>

  <xsl:template mode="divnum"
    match="front/div1 | front//div2 | front//div3 | front//div4 | front//div5"/>

  <xsl:template mode="divnum" match="div2">
    <xsl:number level="multiple" count="div1 | div2" format="1.1 "/>
  </xsl:template>

  <xsl:template mode="divnum" match="back//div2">
    <xsl:number level="multiple" count="div1 | div2 | inform-div1"
      format="A.1 "/>
  </xsl:template>

  <xsl:template mode="divnum" match="div3">
    <xsl:number level="multiple" count="div1 | div2 | div3"
      format="1.1.1 "/>
  </xsl:template>

  <xsl:template mode="divnum" match="back//div3">
    <xsl:number level="multiple"
      count="div1 | div2 | div3 | inform-div1" format="A.1.1 "/>
  </xsl:template>

  <xsl:template mode="divnum" match="div4">
    <xsl:number level="multiple" count="div1 | div2 | div3 | div4"
      format="1.1.1.1 "/>
  </xsl:template>

  <xsl:template mode="divnum" match="back//div4">
    <xsl:number level="multiple"
      count="div1 | div2 | div3 | div4 | inform-div1"
      format="A.1.1.1 "/>
  </xsl:template>

  <xsl:template mode="divnum" match="div5">
    <xsl:number level="multiple"
      count="div1 | div2 | div3 | div4 | div5" format="1.1.1.1.1 "/>
  </xsl:template>

  <xsl:template mode="divnum" match="back//div5">
    <xsl:number level="multiple"
      count="div1 | div2 | div3 | div4 | div5 | inform-div1"
      format="A.1.1.1.1 "/>
  </xsl:template>

  <!-- mode: toc -->
  <xsl:template mode="toc" match="div1">
    <xsl:apply-templates select="." mode="divnum"/>
    <a>
      <xsl:attribute name="href">
        <xsl:call-template name="href.target">
          <xsl:with-param name="target" select="."/>
        </xsl:call-template>
      </xsl:attribute>
      <xsl:apply-templates select="head" mode="text"/>
    </a>
    <br/>
    <xsl:apply-templates select="div2" mode="toc"/>
  </xsl:template>

  <xsl:template mode="toc" match="div2">
    <xsl:text>&#xa0;&#xa0;&#xa0;&#xa0;</xsl:text>
    <xsl:apply-templates select="." mode="divnum"/>
    <a>
      <xsl:attribute name="href">
        <xsl:call-template name="href.target">
          <xsl:with-param name="target" select="."/>
        </xsl:call-template>
      </xsl:attribute>
      <xsl:apply-templates select="head" mode="text"/>
    </a>
    <br/>
    <xsl:apply-templates select="div3" mode="toc"/>
  </xsl:template>

  <xsl:template mode="toc" match="div3">
    <xsl:text>&#xa0;&#xa0;&#xa0;&#xa0;&#xa0;&#xa0;&#xa0;&#xa0;</xsl:text>
    <xsl:apply-templates select="." mode="divnum"/>
    <a>
      <xsl:attribute name="href">
        <xsl:call-template name="href.target">
          <xsl:with-param name="target" select="."/>
        </xsl:call-template>
      </xsl:attribute>
      <xsl:apply-templates select="head" mode="text"/>
    </a>
    <br/>
    <xsl:apply-templates select="div4" mode="toc"/>
  </xsl:template>

  <xsl:template mode="toc" match="div4">
    <xsl:text>&#xa0;&#xa0;&#xa0;&#xa0;&#xa0;&#xa0;&#xa0;&#xa0;&#xa0;&#xa0;&#xa0;&#xa0;</xsl:text>
    <xsl:apply-templates select="." mode="divnum"/>
    <a>
      <xsl:attribute name="href">
        <xsl:call-template name="href.target">
          <xsl:with-param name="target" select="."/>
        </xsl:call-template>
      </xsl:attribute>
      <xsl:apply-templates select="head" mode="text"/>
    </a>
    <br/>
    <xsl:apply-templates select="div5" mode="toc"/>
  </xsl:template>

  <xsl:template mode="toc" match="div5">
    <xsl:text>&#xa0;&#xa0;&#xa0;&#xa0;&#xa0;&#xa0;&#xa0;&#xa0;&#xa0;&#xa0;&#xa0;&#xa0;&#xa0;&#xa0;&#xa0;&#xa0;</xsl:text>
    <xsl:apply-templates select="." mode="divnum"/>
    <a>
      <xsl:attribute name="href">
        <xsl:call-template name="href.target">
          <xsl:with-param name="target" select="."/>
        </xsl:call-template>
      </xsl:attribute>
      <xsl:apply-templates select="head" mode="text"/>
    </a>
    <br/>
  </xsl:template>

  <xsl:template mode="toc" match="inform-div1">
    <xsl:apply-templates select="." mode="divnum"/>
    <a>
      <xsl:attribute name="href">
        <xsl:call-template name="href.target">
          <xsl:with-param name="target" select="."/>
        </xsl:call-template>
      </xsl:attribute>
      <xsl:apply-templates select="head" mode="text"/>
    </a>
    <xsl:text> (Non-Normative)</xsl:text>
    <br/>
    <xsl:apply-templates select="div2" mode="toc"/>
  </xsl:template>

  <xsl:template name="css">
    <style type="text/css">
      <xsl:text>
code           { font-family: monospace; }

div.constraint,
div.issue,
div.note,
div.notice     { margin-left: 2em; }

dt.label       { display: run-in; }

li p           { margin-top: 0.3em;
                 margin-bottom: 0.3em; }
      </xsl:text>
      <xsl:value-of select="$additional.css"/>
    </style>
    <link rel="stylesheet" type="text/css">
      <xsl:attribute name="href">
        <xsl:text>http://www.w3.org/StyleSheets/TR/W3C-</xsl:text>
        <xsl:choose>
          <xsl:when test="/spec/@w3c-doctype='wd'">WD</xsl:when>
          <xsl:when test="/spec/@w3c-doctype='rec'">REC</xsl:when>
          <xsl:when test="/spec/@w3c-doctype='pr'">PR</xsl:when>
          <xsl:when test="/spec/@w3c-doctype='cr'">CR</xsl:when>
          <xsl:otherwise>NOTE</xsl:otherwise>
        </xsl:choose>
        <xsl:text>.css</xsl:text>
      </xsl:attribute>
    </link>
  </xsl:template>

  <xsl:template name="href.target">
    <xsl:param name="target" select="."/>

    <xsl:text>#</xsl:text>

    <xsl:choose>
      <xsl:when test="$target/@id">
        <xsl:value-of select="$target/@id"/>
      </xsl:when>
      <xsl:otherwise>
        <xsl:value-of select="generate-id($target)"/>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:template>

</xsl:transform>
