from flask import Flask, request, jsonify

app = Flask(__name__)

# ---------- Helper ----------
def get_nums():
    data = request.get_json(force=True)
    return float(data["num1"]), float(data["num2"])

# ---------- Routes ----------
@app.post("/add")
def add():
    n1, n2 = get_nums()
    return jsonify(result=n1 + n2)

@app.post("/subtract")
def subtract():
    n1, n2 = get_nums()
    return jsonify(result=n1 - n2)

@app.post("/multiply")
def multiply():
    n1, n2 = get_nums()
    return jsonify(result=n1 * n2)

@app.post("/divide")
def divide():
    n1, n2 = get_nums()
    if n2 == 0:
        return jsonify(error="Division by zero"), 400
    return jsonify(result=n1 / n2)

if __name__ == "__main__":
    # API only — no templates, so default port 5000 is fine
    app.run(debug=True)
