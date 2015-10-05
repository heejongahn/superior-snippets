import urllib.request
import urllib.parse
import requests
import lxml.html
import sys
import os

def require_dir(path):
    try:
        os.makedirs(path)
    except:
        pass

def argument_check():
    if not sys.argv:
        print ("You should give a search keyword as a command line argumnent. ex) python3 crawl.py quote")

    if len(sys.argv) > 2:
        print ("You should give only one search keyword for each time.")

    return sys.argv[1]

def save_img(img_url, directory, query, count):
    filename = os.path.join(directory, '%s-%d.jpg' % (query, count))
    try:
        urllib.request.urlretrieve('http://'+img_url, filename)
    except:
        return 1
    return 0


def main():
    query = argument_check()

    # Saving images to /images folder
    count = 0
    directory = os.path.join(os.path.dirname(os.path.abspath(__file__)), "images")
    require_dir(directory)

    rtext = requests.get('http://images.search.yahoo.com/search/images?p=' +
            query).text

    sourcetree = lxml.html.fromstring(rtext)
    images = sourcetree.cssselect('img')

    img_urls = [urllib.parse.parse_qs(image.getparent().get('href'))['imgurl'][0] for
            image in images if 'bing.net' in (image.get('src') or '')]

    for img_url in img_urls:
        if not save_img(img_url, directory, query, count):
            count += 1

if __name__=='__main__':
    main()
