// This script verifies that a the highlight.js initialization file can be overriden.

import com.jcabi.w3c.ValidatorBuilder
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.jsoup.Jsoup

// Acquires the sample HTML content
def html = new File(basedir, 'target/site/index.html').text

// Validates HTML 5
def htmlResponse = new ValidatorBuilder().html().validate(html)

MatcherAssert.assertThat(
    'There are errors',
    htmlResponse.errors(),
    Matchers.describedAs(htmlResponse.toString(), Matchers.hasSize(0))
)
MatcherAssert.assertThat(
    'There are warnings',
    htmlResponse.warnings(),
    Matchers.describedAs(htmlResponse.toString(), Matchers.hasSize(0))
)

// Parses HTML
def parsed = Jsoup.parse(html)
def body = parsed.body()
def head = parsed.head()

// The custom style file is linked
def customStyle = head.select( 'link[href="./css/custom.css"]' )
assert customStyle != null

// The custom script file is linked
def customScript = body.select( 'script[src="./js/custom.js"]' )
assert customScript != null
