Thank you for your interest in contributing to spring_batch_prototype!

We welcome contributions of all kinds — code, tests, documentation, bug reports, and ideas. This document explains how to get started and what to expect.

Table of contents
- How to get started
- Making changes (branching & commits)
- Running tests and quality checks
- Submitting a pull request
- Reporting bugs and requesting features
- Communication & support
- Code of Conduct
- License

How to get started
1. Fork the repository and clone your fork:
   git clone https://github.com/<your-username>/spring_batch_prototype.git
2. Create a feature branch:
   git checkout -b feat/brief-description
3. Follow the project setup instructions in README.md (including Java and Maven usage).
4. Run the test suite locally before submitting changes:
   ./mvnw test  (or mvn test)

Making changes (branching & commits)
- Keep changes small and focused: one logical change per branch / pull request.
- Use clear, descriptive commit messages (imperative tense): "Fix: validate job parameters" or "Add: README getting-started section".
- Rebase or squash as needed to keep history tidy; maintainers may request changes to commit organization.

Running tests and quality checks
- Run unit tests and integration tests locally before opening a PR.
- If the project uses formatting or lint tools, run them and fix issues locally.
- If you cannot run a specific test locally, document that in the PR and explain what you tried.

Submitting a pull request
1. Push your branch to your fork:
   git push origin feat/brief-description
2. Open a Pull Request from your branch to the repository's default branch.
3. In the PR description include:
   - A short summary of what you changed
   - Motivation / why the change is needed
   - How to test the change (commands, inputs, expected outputs)
   - Any known limitations or follow-up work
4. Link related issues if applicable.
5. Expect review feedback — maintainers will review and may request small changes. We'll aim to respond promptly.

Reporting bugs and requesting features
- Search existing issues before opening a new one.
- When reporting a bug, include:
  - Steps to reproduce
  - Expected vs. actual behavior
  - Environment details (OS, Java version, Maven wrapper or mvn, logs, stack traces)
- For feature requests, explain the use case and any suggested API or UX.

Issue and PR templates
- Use the provided issue and PR templates to ensure consistent information is supplied.

Communication & support
- For general questions, use Discussions (if enabled) or open an issue with the "help wanted" label.
- If you need help getting started, comment on a "good first issue" or mention maintainers in the discussion.

Code of Conduct
This project follows a Code of Conduct to foster an open and welcoming community. By participating, you agree to abide by its terms. See CODE_OF_CONDUCT.md for details.

Maintainer guidelines
- Maintainers will try to review new contributions within a reasonable time (typically 1–7 days).
- We may close or request edits for contributions that are incomplete, out-of-scope, or lacking required information.

License
By contributing, you agree that your contributions will be licensed under the project's license (see LICENSE).

Thank you for helping improve this project — your contributions are appreciated!
