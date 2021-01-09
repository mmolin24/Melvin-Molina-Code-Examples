import setuptools

with open("README.md", "r", encoding="utf-8") as fh:
    long_description = fh.read()

setuptools.setup(
    name="Web-Crawler-Pkg-mmolin24", 
    version="1.0.0",
    author="Melvin Molina",
    author_email="melvinmmolina@gmail.com",
    description="Web Crawler with Parralelism",
    long_description=long_description,
    long_description_content_type="text/markdown",
    url="https://github.com/mmolin24",
    packages=setuptools.find_packages(),
    classifiers=[],
    python_requires='>=3.6',
)