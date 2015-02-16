<?xml version="1.0"?>
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
 

  <xsl:template match="example/head">
    <xsl:text>&#10;</xsl:text>
    <h5>
      <xsl:text>Example: </xsl:text>
      <xsl:apply-templates/>
    </h5>
  </xsl:template>

  <xsl:template match="inform-div1/head">
    <xsl:text>&#10;</xsl:text>
    <h2>
      <a>
        <xsl:attribute name="name">
          <xsl:choose>
            <xsl:when test="../@id">
              <xsl:value-of select="../@id"/>
            </xsl:when>
            <xsl:otherwise>
              <xsl:value-of select="generate-id(..)"/>
            </xsl:otherwise>
          </xsl:choose>
        </xsl:attribute>
      </a>
      <xsl:apply-templates select=".." mode="divnum"/>
      <xsl:apply-templates/>
      <xsl:text> (Non-Normative)</xsl:text>
    </h2>
  </xsl:template>

  <xsl:template match="issue/head">
    <p class="prefix">
      <b><xsl:apply-templates/></b>
    </p>
  </xsl:template>

  <xsl:template match="scrap/head">
    <xsl:text>&#10;</xsl:text>
    <h5>
      <xsl:apply-templates/>
    </h5>
  </xsl:template>

  <xsl:template match="vcnote/head">
    <p class="prefix">
      <xsl:if test="../@id">
	<a name="{../@id}"/>
      </xsl:if>
      <b><xsl:text>Validity constraint: </xsl:text><xsl:apply-templates/></b>
    </p>
  </xsl:template>

  <xsl:template match="wfcnote/head">
    <p class="prefix">
      <xsl:if test="../@id">
	<a name="{../@id}"/>
      </xsl:if>
      <b><xsl:text>Well-formedness constraint: </xsl:text><xsl:apply-templates/></b>
    </p>
  </xsl:template>

  <!-- header: metadata about the spec -->
  <!-- pull out information into standard W3C layout -->
  <xsl:template match="header">
    <div class="head">
      <p>
        <a href="http://www.w3.org/">
          <img src="http://www.w3.org/Icons/w3c_home"
            alt="W3C" height="48" width="72"/>
        </a>
      </p>
      <xsl:text>&#10;</xsl:text>
      <h1>
        <xsl:apply-templates select="title"/>
        <xsl:if test="version">
          <xsl:text> </xsl:text>
          <xsl:apply-templates select="version"/>
        </xsl:if>
      </h1>
      <xsl:if test="subtitle">
        <xsl:text>&#10;</xsl:text>
        <h2>
          <xsl:apply-templates select="subtitle"/>
        </h2>
      </xsl:if>
      <xsl:text>&#10;</xsl:text>
      <h2>
        <xsl:apply-templates select="w3c-doctype"/>
        <xsl:text> </xsl:text>
        <xsl:if test="pubdate/day">
          <xsl:apply-templates select="pubdate/day"/>
          <xsl:text> </xsl:text>
        </xsl:if>
        <xsl:apply-templates select="pubdate/month"/>
        <xsl:text> </xsl:text>
        <xsl:apply-templates select="pubdate/year"/>
      </h2>
      <dl>
        <xsl:apply-templates select="publoc"/>
        <xsl:apply-templates select="latestloc"/>
        <xsl:apply-templates select="prevlocs"/>
        <xsl:apply-templates select="authlist"/>
      </dl>
      <p class="copyright"><a href="http://www.w3.org/Consortium/Legal/ipr-notice-20000612#Copyright"> Copyright</a> &#xa9;2001 <a href="http://www.w3.org/"><abbr title="World Wide Web Consortium">W3C</abbr></a><sup>&#xae;</sup> (<a href="http://www.lcs.mit.edu/"><abbr title="Massachusetts Institute of Technology">MIT</abbr></a>, <a href="http://www.inria.fr/"><abbr lang="fr" title="Institut National de Recherche en Informatique et Automatique">INRIA</abbr></a>, <a href="http://www.keio.ac.jp/">Keio</a>), All Rights Reserved. W3C <a href="http://www.w3.org/Consortium/Legal/ipr-notice-20000612#Legal_Disclaimer">liability</a>, <a href="http://www.w3.org/Consortium/Legal/ipr-notice-20000612#W3C_Trademarks">trademark</a>, <a href="http://www.w3.org/Consortium/Legal/copyright-documents-19990405">document use</a> and <a href="http://www.w3.org/Consortium/Legal/copyright-software-19980720">software licensing</a> rules apply.</p>
    </div>
    <hr/>
    <xsl:apply-templates select="notice"/>
    <xsl:apply-templates select="abstract"/>
    <xsl:apply-templates select="status"/>
  </xsl:template>

  <!-- inform-div1: non-normative back matter top-level division -->
  <!-- treat like div1 except add "(Non-Normative)" to title -->
  <xsl:template match="inform-div1">
    <div class="div1">
      <xsl:apply-templates/>
    </div>
  </xsl:template>

  <!-- interface: -->
  <!-- IDL stuff isn't handled yet -->

  <!-- issue: open issue before the Working Group -->
  <!-- maintain an ID for linking to it -->
  <!-- currently generates boilerplate head plus optional head child
       element; this should probably be cleaned up to only use the
       head if it's present -->
  <xsl:template match="issue">
    <div class="issue">
      <p class="prefix">
	<xsl:if test="@id">
	  <a name="{@id}"/>
	</xsl:if>
	<b><xsl:text>Issue (</xsl:text><xsl:value-of select="@id"/><xsl:text>):</xsl:text></b>
      </p>
      <xsl:apply-templates/>
    </div>
  </xsl:template>

  <!-- item: generic list item -->
  <xsl:template match="item">
    <li>
      <xsl:apply-templates/>
    </li>
  </xsl:template>

  <!-- kw: keyword -->
  <!-- make it bold -->
  <xsl:template match="kw">
    <b><xsl:apply-templates/></b>
  </xsl:template>

  <!-- label: term for defintion in glossary entry -->
  <!-- already in <dl> context from glist -->
  <xsl:template match="label">
    <dt class="label">
      <xsl:if test="@id"><a name="{@id}"/></xsl:if>
      <xsl:apply-templates/>
    </dt>
  </xsl:template>

  <!-- language: -->
  <!-- langusage: -->
  <!-- identify language usage within a spec; not actually formatted -->

  <!-- latestloc: latest location for this spec -->
  <!-- called in a <dl> context from header -->
  <xsl:template match="latestloc">
    <dt>Latest version:</dt>
    <dd>
      <xsl:apply-templates/>
    </dd>
  </xsl:template>

  <!-- lhs: left-hand side of formal productions -->
  <!-- make a table row with the lhs and the corresponding other
       pieces in this crazy mixed-up content model -->
  <xsl:template match="lhs">
    <tr valign="baseline">
      <td>
	<xsl:if test="ancestor-or-self::*/@diff and $show.diff.markup='1'">
	  <xsl:attribute name="class">
	    <xsl:text>diff-</xsl:text>
	    <xsl:value-of select="ancestor-or-self::*/@diff"/>
	  </xsl:attribute>
	</xsl:if>
	<xsl:if test="../@id">
	  <a name="{../@id}"/>
	</xsl:if>
	<xsl:apply-templates select="ancestor::prod" mode="number"/>
<!--
  This could be done right here, but XT goes into deep space when the
  node to be numbered isn't the current node and level="any":
          <xsl:number count="prod" level="any" from="spec"
            format="[1]"/>
  -->
	<xsl:text>&#xa0;&#xa0;&#xa0;</xsl:text>
      </td>
      <td>
	<xsl:if test="ancestor-or-self::*/@diff and $show.diff.markup='1'">
	  <xsl:attribute name="class">
	    <xsl:text>diff-</xsl:text>
	    <xsl:value-of select="ancestor-or-self::*/@diff"/>
	  </xsl:attribute>
	</xsl:if>
        <code><xsl:apply-templates/></code>
      </td>
      <td>
	<xsl:if test="ancestor-or-self::*/@diff and $show.diff.markup='1'">
	  <xsl:attribute name="class">
	    <xsl:text>diff-</xsl:text>
	    <xsl:value-of select="ancestor-or-self::*/@diff"/>
	  </xsl:attribute>
	</xsl:if>
        <xsl:text>&#xa0;&#xa0;&#xa0;::=&#xa0;&#xa0;&#xa0;</xsl:text>
      </td>
      <xsl:apply-templates
        select="following-sibling::*[1][name()='rhs']"/>
    </tr>
  </xsl:template>

  <!-- loc: a Web location -->
  <!-- outside the header, it's a normal cross-reference -->
  <xsl:template match="loc">
    <a href="{@href}">
      <xsl:apply-templates/>
    </a>
  </xsl:template>

  <!-- member: member of an organization -->
  <!-- appears only in orglist, which creates <ul> context -->
  <xsl:template match="member">
    <li>
      <xsl:apply-templates/>
    </li>
  </xsl:template>

  <!-- method: -->
  <!-- IDL stuff isn't handled yet -->

  <!-- module: -->
  <!-- IDL stuff isn't handled yet -->

  <!-- month: month of spec -->
  <!-- only used in pudate; called directly from header template -->

  <!-- name: name of an editor or organization member -->
  <!-- only appears in author and member -->
  <!-- just output text -->
  <xsl:template match="name">
    <xsl:apply-templates/>
  </xsl:template>

  <!-- note: a note about the spec -->
  <xsl:template match="note">
    <div class="note">
      <p class="prefix">
        <b>Note:</b>
      </p>
      <xsl:apply-templates/>
    </div>
  </xsl:template>

  <!-- notice: a front-matter advisory about the spec's status -->
  <!-- make sure people notice it -->
  <xsl:template match="notice">
    <div class="notice">
      <p class="prefix">
        <b>NOTICE:</b>
      </p>
      <xsl:apply-templates/>
    </div>
  </xsl:template>

  <!-- nt: production non-terminal -->
  <!-- make a link to the non-terminal's definition -->
  <xsl:template match="nt">
    <a>
      <xsl:attribute name="href">
        <xsl:call-template name="href.target">
          <xsl:with-param name="target" select="id(@def)"/>
        </xsl:call-template>
      </xsl:attribute>
      <xsl:apply-templates/>
    </a>
  </xsl:template>

  <!-- olist: an ordered list -->
  <xsl:template match="olist">
    <ol>
      <xsl:apply-templates/>
    </ol>
  </xsl:template>

  <!-- orglist: a list of an organization's members -->
  <xsl:template match="orglist">
    <ul>
      <xsl:apply-templates/>
    </ul>
  </xsl:template>

  <!-- p: a standard paragraph -->
  <xsl:template match="p">
    <p>
      <xsl:if test="@id">
        <xsl:attribute name="id">
          <xsl:value-of select="@id"/>
        </xsl:attribute>
      </xsl:if>
      <xsl:if test="@role">
        <xsl:attribute name="class">
          <xsl:value-of select="@role"/>
        </xsl:attribute>
      </xsl:if>
      <xsl:apply-templates/>
    </p>
  </xsl:template>

  <!-- param: -->
  <!-- IDL stuff isn't handled yet -->

  <!-- parameters: -->
  <!-- IDL stuff isn't handled yet -->

  <!-- phrase: semantically meaningless markup hanger -->
  <!-- role attributes may be used to request different formatting,
       which isn't currently handled -->
  <xsl:template match="phrase">
    <xsl:apply-templates/>
  </xsl:template>

  <!-- prevlocs: previous locations for this spec -->
  <!-- called in a <dl> context from header -->
  <xsl:template match="prevlocs">
    <dt>Previous versions:</dt>
    <dd>
      <xsl:apply-templates/>
    </dd>
  </xsl:template>

  <!-- prod: a formal grammar production -->
  <!-- if not in a prodgroup, needs a <tbody> -->
  <!-- has a weird content model; makes a table but there are no
       explicit rules; many different things can start a new row -->
  <!-- process the first child in each row, and it will process the
       others -->
  <xsl:template match="prod">
    <tbody>
      <xsl:apply-templates
        select="lhs |
                rhs[preceding-sibling::*[1][name()!='lhs']] |
                com[preceding-sibling::*[1][name()!='rhs']] |
                constraint[preceding-sibling::*[1][name()!='rhs']] |
                vc[preceding-sibling::*[1][name()!='rhs']] |
                wfc[preceding-sibling::*[1][name()!='rhs']]"/>
    </tbody>
  </xsl:template>

  <xsl:template match="prodgroup/prod">
    <xsl:apply-templates
      select="lhs |
              rhs[preceding-sibling::*[1][name()!='lhs']] |
              com[preceding-sibling::*[1][name()!='rhs']] |
              constraint[preceding-sibling::*[1][name()!='rhs']] |
              vc[preceding-sibling::*[1][name()!='rhs']] |
              wfc[preceding-sibling::*[1][name()!='rhs']]"/>
  </xsl:template>

  <!-- prodgroup: group of formal productions -->
  <!-- create one <tbody> for each group -->
  <xsl:template match="prodgroup">
    <tbody>
      <xsl:apply-templates/>
    </tbody>
  </xsl:template>

  <!-- prodrecap: reiteration of a prod -->
  <!-- process the prod in another node that will never generate a
       <tbody> or a number, plus links the lhs to the original
       production -->
  <xsl:template match="prodrecap">
    <tbody>
      <xsl:apply-templates select="id(@ref)" mode="ref"/>
    </tbody>
  </xsl:template>

  <!-- proto: function prototype -->
  <!-- type and name of the function, with arguments in parens -->
  <xsl:template match="proto">
    <p>
      <em><xsl:value-of select="@return-type"/></em>
      <xsl:text> </xsl:text>
      <b><xsl:value-of select="@name"/></b>
      <xsl:text>(</xsl:text>
      <xsl:apply-templates/>
      <xsl:text>)</xsl:text>
    </p>
  </xsl:template>

  <!-- pubdate: date of spec -->
  <!-- called directly from header -->

  <!-- publoc: location of current version of spec -->
  <!-- called from header in <dl> context -->
  <xsl:template match="publoc">
    <dt>This version:</dt>
    <dd>
      <xsl:apply-templates/>
    </dd>
  </xsl:template>

  <!-- pubstmt: statement of publication -->
  <!-- not currently output -->

  <!-- quote: a quoted string or phrase -->
  <!-- it would be nice to use HTML <q> elements, but browser support
       is abysmal -->
  <xsl:template match="quote">
    <xsl:text>"</xsl:text>
    <xsl:apply-templates/>
    <xsl:text>"</xsl:text>
  </xsl:template>

  <!-- raises: -->
  <!-- IDL stuff isn't handled yet -->

  <!-- reference: -->
  <!-- IDL stuff isn't handled yet -->

  <!-- resolution: resolution of an issue -->
  <xsl:template match="resolution">
    <p class="prefix">
      <b>Resolution:</b>
    </p>
    <xsl:apply-templates/>
  </xsl:template>

  <!-- returns: -->
  <!-- IDL stuff isn't handled yet -->

  <!-- revisiondesc: description of spec revision -->
  <!-- used for internal tracking; not formatted -->

  <!-- rhs: right-hand side of a formal production -->
  <!-- make a table cell; if it's not the first after an LHS, make a
       new row, too -->
  <xsl:template match="rhs">
    <xsl:choose>
      <xsl:when test="preceding-sibling::*[1][name()='lhs']">
        <td>
	  <xsl:if test="ancestor-or-self::*/@diff and $show.diff.markup='1'">
	    <xsl:attribute name="class">
	      <xsl:text>diff-</xsl:text>
	      <xsl:value-of select="ancestor-or-self::*/@diff"/>
	    </xsl:attribute>
	  </xsl:if>
          <code><xsl:apply-templates/></code>
        </td>
        <xsl:apply-templates
          select="following-sibling::*[1][name()='com' or
                                          name()='constraint' or
                                          name()='vc' or
                                          name()='wfc']"/>
      </xsl:when>
      <xsl:otherwise>
        <tr valign="baseline">
          <td/><td/><td/>
          <td>
	    <xsl:if test="ancestor-or-self::*/@diff and $show.diff.markup='1'">
	      <xsl:attribute name="class">
		<xsl:text>diff-</xsl:text>
		<xsl:value-of select="ancestor-or-self::*/@diff"/>
	      </xsl:attribute>
	    </xsl:if>
            <code><xsl:apply-templates/></code>
          </td>
          <xsl:apply-templates
            select="following-sibling::*[1][name()='com' or
                                            name()='constraint' or
                                            name()='vc' or
                                            name()='wfc']"/>
        </tr>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:template>

  <!-- role: part played by a member of an organization -->
  <xsl:template match="role">
    <xsl:text> (</xsl:text>
    <i><xsl:apply-templates/></i>
    <xsl:text>) </xsl:text>
  </xsl:template>

  <!-- scrap: series of formal grammar productions -->
  <!-- set up a <table> and handle children -->
  <xsl:template match="scrap">
    <xsl:apply-templates select="head"/>
    <table class="scrap" summary="Scrap">
      <xsl:apply-templates select="bnf | prod | prodgroup"/>
    </table>
  </xsl:template>

  <!-- sequence: -->
  <!-- IDL stuff isn't handled yet -->

  <!-- sitem: simple list item -->
  <!-- just make one paragraph with <br>s between items -->
  <xsl:template match="sitem[position() &gt; 1]">
    <br/>
    <xsl:apply-templates/>
  </xsl:template>

  <!-- slist: simple list -->
  <!-- using a <blockquote> to indent the list is very wrong, but it
       works -->
  <xsl:template match="slist">
    <blockquote>
      <p>
        <xsl:apply-templates/>
      </p>
    </blockquote>
  </xsl:template>

  <!-- source: the source of an issue -->
  <xsl:template match="source">
    <p>
      <b>Source</b>
      <xsl:text>: </xsl:text>
      <xsl:apply-templates/>
    </p>
  </xsl:template>

  <!-- sourcedesc: description of spec preparation -->
  <!-- used for tracking the source, but not formatted -->

  <!-- spec: the specification itself -->
  <xsl:template match="spec">
    <html>
      <xsl:if test="header/langusage/language">
        <xsl:attribute name="lang">
          <xsl:value-of select="header/langusage/language/@id"/>
        </xsl:attribute>
      </xsl:if>
      <head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
        <title>
          <xsl:apply-templates select="header/title"/>
          <xsl:if test="header/version">
            <xsl:text> </xsl:text>
            <xsl:apply-templates select="header/version"/>
          </xsl:if>
          <xsl:if test="$additional.title != ''">
            <xsl:text> -- </xsl:text>
            <xsl:value-of select="$additional.title"/>
	  </xsl:if>
        </title>
        <xsl:call-template name="css"/>
      </head>
      <body>
        <xsl:apply-templates/>
        <xsl:if test="//footnote">
          <hr/>
          <div class="endnotes">
            <xsl:text>&#10;</xsl:text>
            <h3>
              <a name="endnotes">
                <xsl:text>End Notes</xsl:text>
              </a>
            </h3>
            <dl>
              <xsl:apply-templates select="//footnote" mode="notes"/>
            </dl>
          </div>
        </xsl:if>
      </body>
    </html>
  </xsl:template>
  <!-- status: the status of the spec -->
  <xsl:template match="status">
    <div>
      <xsl:text>&#10;</xsl:text>
      <h2>
        <a name="status">Status of this Document</a>
      </h2>
      <xsl:apply-templates/>
    </div>
  </xsl:template>

  <!-- struct: -->
  <!-- IDL stuff isn't handled yet -->

  <!-- sub: subscript -->
  <xsl:template match="sub">
    <sub>
      <xsl:apply-templates/>
    </sub>
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
