// This script verifies that a minimal site contains only the barebones of a site.

import org.jsoup.Jsoup

// Acquires the sample HTML content
def html = new File(basedir, 'target/site/index.html').text

// Parses HTML
def parsed = Jsoup.parse(html)
def body = parsed.body()

// Images
def fig = body.select( 'figure' ).first()
assert fig.hasClass( 'shadow' )
assert fig.hasClass( 'bg-dark' )
assert fig.hasClass( 'rounded' )
assert fig.hasClass( 'p-2' )

def firstImg = fig.select( 'img' ).first()
assert firstImg.attr( 'src' ).contains( './images/example_class_diagram.png' )
assert firstImg.attr( 'alt' ).contains( 'Dice class diagram' )
assert firstImg.hasClass( 'img-fluid' )

def firstCaption = fig.select( 'figcaption' ).first()
assert firstCaption.html().contains( 'Dice class diagram' )
assert firstCaption.hasClass( 'font-italic' )
