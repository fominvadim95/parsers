<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/">
        <html>
            <head>
                <meta charset="UTF-8"/>
                <title>Players</title>
                <!-- Latest compiled and minified CSS -->
                <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"/>
                <link href="../css/main.css" rel="stylesheet" type="text/css"/>
                <link href="../css/sticky_footer.css" rel="stylesheet" type="text/css"/>
            </head>
            <body>

                <div class="container">
                    <table class="table table-hover">

                        <tr>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Country</th>
                            <th>Age</th>
                            <th>Cost</th>
                            <th>Position</th>
                        </tr>

                        <xsl:for-each select="teams/team/players/player">
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
                                <td>
                                    <xsl:value-of select="cost"/>
                                </td>
                                <td>
                                    <xsl:value-of select="position"/>
                                </td>
                            </tr>
                        </xsl:for-each>

                    </table>
                </div>

                <footer class="footer">
                    <div class="container">
                        <p class="text-muted">© V. Panasenko, V. Fomin, U. Ostapenko. 2017.</p>
                    </div>
                </footer>

            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>