import { serve } from "https://deno.land/std@0.224.0/http/server.ts";

serve(async (req) => {

  console.log("========== NEW REQUEST ==========");

  if (req.method !== "POST") {

    console.log("Invalid HTTP Method:", req.method);

    return new Response("Method Not Allowed", {
      status: 405,
    });
  }

  try {

    const { message, context } = await req.json();

    console.log("Message:", message);
    console.log("Context:", JSON.stringify(context));

    const apiKey = Deno.env.get("GEMINI_API_KEY");

    console.log("API Key Exists:", !!apiKey);

    if (!apiKey) {

      return new Response(
        JSON.stringify({
          error: "Gemini API key not configured."
        }),
        {
          status: 500,
          headers: {
            "Content-Type": "application/json"
          }
        }
      );
    }

    const prompt = `
    You are Expo Assistant, the built-in AI inside the ExpoPlus expense tracker app.

    You are NOT ChatGPT.
    You NEVER mention that you are an AI model.

    Your personality:

    - Friendly and professional
    - Speak naturally like a finance coach
    - Keep responses short (2-5 sentences)
    - Be direct
    - Never start every reply with "Hello", "Hi", or greetings unless the user greets first.
    - Never use Markdown.
    - Never use *, **, bullet markdown or headings.
    - Never wrap words in quotes.
    - Never mention dollars ($).
    - Always assume the currency is Indian Rupees (₹).
    - If talking about money, always write ₹ before the amount.
    - If amount is unknown, simply say "your spending" instead of inventing numbers.
    - Give practical suggestions based on the user's expense data.
    - Sound conversational instead of robotic.
    - Avoid repeating yourself.
    - End naturally instead of asking unnecessary follow-up questions every time.

    Financial Context:
    ${JSON.stringify(context, null, 2)}

    User Question:
    ${message}
    `;

    console.log("Calling Gemini...");

    const response = await fetch(
      `https://generativelanguage.googleapis.com/v1beta/models/gemini-flash-lite-latest:generateContent?key=${apiKey}`,
      {
        method: "POST",
        headers: {
          "Content-Type": "application/json"
        },
        body: JSON.stringify({

          contents: [
            {
              role: "user",
              parts: [
                {
                  text: prompt
                }
              ]
            }
          ],

          generationConfig: {

            temperature: 0.35,

            topP: 0.95,

            maxOutputTokens: 256
          }
        })
      }
    );

    console.log("Gemini Status:", response.status);

    const data = await response.json();

    console.log("Gemini Response:");
    console.log(JSON.stringify(data));

    if (!response.ok) {

      console.error("Gemini returned error.");

      return new Response(
        JSON.stringify(data),
        {
          status: response.status,
          headers: {
            "Content-Type": "application/json"
          }
        }
      );
    }

    const reply =
      data.candidates?.[0]?.content?.parts?.[0]?.text ??
      "Sorry, I couldn't generate a response.";

    console.log("Reply Generated Successfully");

    return new Response(
      JSON.stringify({
        reply
      }),
      {
        headers: {
          "Content-Type": "application/json"
        }
      }
    );

  } catch (error) {

    console.error("Unhandled Function Error:");
    console.error(error);

    return new Response(
      JSON.stringify({
        error: error instanceof Error
          ? error.message
          : "Unknown error"
      }),
      {
        status: 500,
        headers: {
          "Content-Type": "application/json"
        }
      }
    );
  }

});