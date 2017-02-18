<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:variable name="teamID">t1</xsl:variable>

    <xsl:template match="teams/team[@id=$teamID]">

        <html>
            <head>
                <meta charset="UTF-8"/>
                <title>Players/Sponsors</title>
                <!-- Latest compiled and minified CSS -->
                <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"/>
                <link href="../css/main.css" rel="stylesheet" type="text/css"/>
                <link href="../css/sticky_footer.css" rel="stylesheet" type="text/css"/>
            </head>
            <body>

                <div class="container-fluid bg-1 text-center" style="background-color: #1abc9c;">
                    <h1>Team: <xsl:value-of select="general/name"/></h1>
                    <h2>Country: <xsl:value-of select="general/country"/></h2>
                    <h3>Coach: <xsl:value-of select="coach/general/name"/></h3>
                    <h3>Stadium: <xsl:value-of select="stadium/general/name"/></h3>
                    <img src="..//image/emblem.jpg"
                         class="img-responsive img-circle margin"
                         style="display:inline" alt="Barca" width="350" height="350"/>
                </div>

                <xsl:call-template name="players">
                    <xsl:with-param name="playersHeader">Players</xsl:with-param>
                </xsl:call-template>

                <xsl:call-template name="sponsors">
                    <xsl:with-param name="sponsorsHeader">Sponsors</xsl:with-param>
                </xsl:call-template>

                <footer class="footer">
                    <div class="container">
                        <p class="text-muted">© V. Panasenko, V. Fomin, U. Ostapenko. 2017.</p>
                    </div>
                </footer>

            </body>
        </html>
    </xsl:template>


    <xsl:template name="players">
        <xsl:param name="playersHeader"/>
        <div class="container">
            <div class="text-center">
                <h3><xsl:value-of select="$playersHeader"/></h3>
            </div>
            <table class="table table-hover">

                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Country</th>
                    <th>Age</th>
                    <th>Cost</th>
                    <th>Position</th>
                </tr>

                <xsl:for-each select="players/player">
                    <tr>
                        <td>
                            <xsl:value-of select="@id"/>
                        </td>
                        <td>
                            <xsl:value-of select="general/name"/>
                        </td>
                        <td>
                            <xsl:value-of select="general/country"/>
                        </td>
                        <td>
                            <xsl:value-of select="general/age"/>
                        </td>
                        <xsl:choose>
                            <xsl:when test="cost > 100">
                                <td bgcolor="#98FB98">
                                    <xsl:value-of select="cost"/>
                                </td>
                            </xsl:when>
                            <xsl:otherwise>
                                <td bgcolor="#FF4040">
                                    <xsl:value-of select="cost"/>
                                </td>
                            </xsl:otherwise>
                        </xsl:choose>
                        <td>
                            <xsl:value-of select="position"/>
                        </td>
                    </tr>
                </xsl:for-each>

            </table>
        </div>
    </xsl:template>


    <xsl:template name="sponsors">
        <xsl:param name="sponsorsHeader"/>

        <div class="container">
            <div class="text-center">
                <h3><xsl:value-of select="$sponsorsHeader"/></h3>
            </div>
            <table class="table table-hover">

                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Country</th>
                    <th>Year</th>
                </tr>

                <xsl:for-each select="sponsors/sponsor">
                    <xsl:sort select="year" data-type="number" order="descending"/>
                    <tr>
                        <td>
                            <xsl:value-of select="@id"/>
                        </td>
                        <td>
                            <xsl:value-of select="general/name"/>
                        </td>
                        <td>
                            <xsl:value-of select="general/country"/>
                        </td>
                        <td>
                            <xsl:value-of select="year"/>
                        </td>
                    </tr>
                </xsl:for-each>

            </table>
        </div>


    </xsl:template>

</xsl:stylesheet>