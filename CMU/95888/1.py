def preprocess text(text):text = text.lower( )text = text.translate(str.maketrans(", ", string.punctuation))text = re.sub(r'\w*\d w*'，'，text)''.join(text.split())text =return text
# Load the DataFrames
resumes df = pd.read excel('test cv.xlsx')
jobs df = pd.read excel('test.xlsx')
# Preprocess the text dataresumes df['resume'] = resumes df['resume' ].apply(preprocess text)
jobs df('Job Description'] = jobs df['Job Description'].apply(preprocess text# Extract the most frequent skills from job descriptions using CountVectorizercount vectorizer = CountVectorizer(stop words='english', max features=10)
words matrix = count vectorizer.fit transform(jobs df['job Description']sum words : words matrix,sum(axis=0words freg = [(word, sum words[0, idx]) for word, idx in count _vectorizer.vocabulary_. items())words freg = sorted(words freg,
skills list = [word[0] for word in words freg]
#Initialize TfidfVectorizervectorizer = TfidfVectorizer(vocabulary=skills list)vectorizer. fit(pd. concat( [resumes df['resume'], jobs df['Job Description']]) )
# Compute similarity scores for each skillresults df = pd.DataFrame( columns=['name'
for i, resume row in resumes df. iterrows( ):resume vector = vectorizer.transform( [resume row[' resume']])for j,job_row in jobs df iterrows( ):iob vector = vectorizer.transform( [job row['Job Description']])similarity_ score = cosine similarity( resume vector, job vector)[0][0]
# Calculating the match percentage for each skillskills match percentage =for skill in skills list;skills match percentage[skill] = round(similarity score * 100, 2) # Converting to percentage
# Appending the result to the results DataFrameresult row = name ' : resume row[ ' name ' ]
'company': job row['Company Name']**skills match percentageresults df = results df.append( result row, ignore index=True)# Print the results DataFrameprint(results df

def preprocess text(text):
    text = text.lower( )
    text = text.translate(str.maketrans('", "', string.punctuation))
    text = re.sub(r'\w*\d\w*', ' ', text)
    text = ' '.join(text.split())
            text =return text