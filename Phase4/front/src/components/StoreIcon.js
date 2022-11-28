import classNames from "classnames";
import algorithm from "../assets/icon--algorithm.svg";
import automation from "../assets/icon--automation.svg";
import computerArchitecture from "../assets/icon--computer-architecture.svg";
import dataScience from "../assets/icon--data-science.svg";
import deepLearning from "../assets/icon--deep-learning.svg";
import defaultIcon from "../assets/icon--default.svg";
import django from "../assets/icon--django.svg";
import ds from "../assets/icon--ds.svg";
import fourthRevolution from "../assets/icon--fourth-revolution.svg";
import git from "../assets/icon--git.svg";
import introToComputer from "../assets/icon--intro-to-computer.svg";
import java from "../assets/icon--java.svg";
import jquery from "../assets/icon--jquery.svg";
import js from "../assets/icon--js.svg";
import machineLearning from "../assets/icon--machine-learning.svg";
import nodeJs from "../assets/icon--node-js.svg";
import oop from "../assets/icon--oop.svg";
import python from "../assets/icon--python.svg";
import react from "../assets/icon--react.svg";
import sql from "../assets/icon--sql.svg";
import unix from "../assets/icon--unix.svg";
import webPublishing from "../assets/icon--web-publishing.svg";

import styles from "./StoreIcon.module.css";

const ICONS = {
  algorithm: algorithm,
  automation: automation,
  "computer-architecture": computerArchitecture,
  "data-science": dataScience,
  "deep-learning": deepLearning,
  default: defaultIcon,
  django: django,
  ds: ds,
  "fourth-revolution": fourthRevolution,
  git: git,
  "intro-to-computer": introToComputer,
  java: java,
  jquery: jquery,
  js: js,
  "machine-learning": machineLearning,
  "node-js": nodeJs,
  oop: oop,
  python: python,
  react: react,
  sql: sql,
  unix: unix,
  "web-publishing": webPublishing,
};

function StoreIcon({ className, photoUrl = "default" }) {
  return (
    <img
      className={classNames(styles.storeIcon, className)}
      src={ICONS[photoUrl]}
      alt={photoUrl}
    />
  );
}

export default StoreIcon;
