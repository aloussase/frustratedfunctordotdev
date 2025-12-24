# Christmas Reflections on AI: 2025 Edition

Author: Alexander Goussas

Date: 24/12/2025

This post is inspired on this YouTube video: https://www.youtube.com/watch?v=S1uKhBd72oI.

## How I used AI in 2025

I would definitely not call myself AI savvy. I use the basic tools everyone uses, and a little bit more. These include
ChatGPT, Claude and clients such as opencode with models made available through proxy servers. I have also made a
couple of MCP servers myself to automate stuff at home and work. But I would say this is the full extent of my AI
knowledge. So, in this post I describe how I used AI in 2025 as a "normal" user.

### Running investigations

Many a time have I found myself in the need of comparing two gargantuan JSON documents for differences related
to certain fields nested 1000 levels deep in the tree.

In the past, I would use my jq skills to painfully perform this investigation manually. I would craft
command lines capable of summoning demons from the depths of hell to find the different kinds of products in a maze of
weirdly concocted JSON payloads. Luckily, those days are long gone. Now, I just save the JSON documents to a file and
ask an AI agent to run the analysis for me, generating HTML reports if necessary.

The results are almost always enough for my needs, though sometimes I have to double-check just in case. I am not at the
point yet where I trust these tools blindly, because I've seen them make really stupid mistakes and other very subtle
ones. The latter are the most dangerous ones and the ones that keep me wary.

### Generating boilerplate code

Another use case for me is generating boilerplate code. Specifically, tests and performing refactorings. I already know
how I want these things to be done, I just don't want to have to do them myself. This is really something I would
call "robot work."

With well specified acceptance criteria at hand and enough context from the codebase, the AI agent almost always does a
great job of generating tests. Granted, I have mostly done these things in statically typed languages, so I don't know
how the tool would perform in the wilderness of Python or JavaScript.

### Language learning

Outside of programming, I use AI tools, mainly ChatGPT, to explain grammar concepts and generate sentences in any
language that I might be learning at a given time. The conversation process by which I discover different aspects of the
language is very dynamic and engaging.

I have it generate example sentences in a specific format and explain any interesting grammar concepts present in the
sentences, and then I use these sentences to create flashcards in Anki.

If you want to know more about using AI for language learning, I can recommend
the [Fluent Forever book](https://www.amazon.com/Fluent-Forever-Revised-Language-Forget/dp/B0DLPFXX1X/ref=sr_1_1?dib=eyJ2IjoiMSJ9.OxKM2WhhNMjX48WRvyG6bDYj3EZhgreD_aPbZsHCZNdziICjSovA8WkCLYoPaiM0935b-ebPbyZZEs_lU7t4ZgPUz-gAxlPguSsHKRJ-GSCXZQwVrlGoeXAJHsiRHijsf0cjEPz0IZu_jsfbdSCZQG4JrHlAQz6E19L8P-X0jTjz5rG2C31YxuXlZS1plVONLC7mgAhj3xDt3OEa5F83hTfgI-Z9Z3vsNfYIpcReM-I.vSOhlAO1NL0wpnO0RI_OfmGVzlCIm6uvYv_VgHdF43I&dib_tag=se&keywords=fluent+forever&qid=1766583937&sr=8-1).

I have also created MCPs that interact with online dictionaries and Anki to automate the card creation flow. This
was an interesting endeavor and worked wonderfully. Nonetheless, I have since stopped doing this since I have learned
that the actual process of creating the flashcard plays a vital part in language acquisition.

## AI in enterprise

I work at one company, so I cannot generalize what we do here to the rest of the world. Nonetheless, my company is
pretty big, so I dare assume it _must_ be somewhat representative of what's going on in general.

In summary, the company I work at is full-on on the AI train. They give each developer a budget to use the latest
and greatest models, all the fancy and shiny tools such, and actively encourage us to use them by
keeping metrics of AI usage per squad.

From out code editors, to our observability dashboards and our video-conferencing tools.
Literally every tool we use at work has some sort of AI integration. Hell, even the freaking SQL console has a
dammed popover offering its SQL generating services to me.

I'd be lying if I said this does not annoy me at times, but it does seem to be the way of the future, and so I embrace
it.

## Final thoughts

I think all of this is great, but I must admit there are things that trouble me a bit.

I've seen developers in many accounts relying on AI tools to fix and understand a given problem because they do not have
the skill to do so. Developers for which their first instinct when faced with a problem is to throw it into the AI
chatbox. For me, AI is a time-saving tool. That's it.

If for you the value of AI comes from the fact that it can do things better than you, then I will blatantly say that
from that moment on the AI tool is worth more than you. Meaning, it can replace you. And it eventually will, because
now you represent money wasted for someone.

I honestly don't know if it's always been like this, but I have met and worked with many developers that are clueless
about the programming language and other tools they use. In these instances, I would gladly exchange them for ChatGPT or
whatever, because the AI tool is actually more useful than them.

Nonetheless, humans learn, and they can learn more and faster than AI tools. At least today, but I fear it might not be
like that for much longer. I don't like romanticizing stuff. Yes, we have business rules to understand. Yes, there
are many edge cases that are hard represent in a structured way. But let's face it, the bulk of the work CRUD
developers do can easily be done by AI tools.

To finish, I'll reiterate that I am not AI savvy. I don't know where it's headed, I only know what I see today.
And what I see is that AI can replace the type of developer that does not care to master their craft, that lets the
years go by and learns nothing, the kind that is a typing monkey. I'm not saying that it should replace them, because
I don't think it should, but it absolutely can.