from flask import Flask, render_template, request
import requests

app = Flask(__name__)
API_BASE = "http://localhost:5000"

@app.get("/")
def home():
    return render_template("index.html")

@app.post("/calculate")
def calculate():
    num1 = request.form["num1"]
    num2 = request.form["num2"]
    op   = request.form["operation"]

    endpoint = f"{API_BASE}/{op}"
    payload  = {"num1": num1, "num2": num2}

    try:
        res= requests.post(endpoint, json=payload, timeout=3)
        res.raise_for_status()
        result = res.json().get("result")
    except Exception as e:
        result = f"Error: {e}"

    return render_template("result.html", result=result)

if __name__ == "__main__":
    app.run(debug=True, port=3000)
