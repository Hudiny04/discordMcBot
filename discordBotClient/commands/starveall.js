const { SlashCommandBuilder } = require('@discordjs/builders');
const axios = require('axios');
require('dotenv').config();

module.exports = {
    data: new SlashCommandBuilder()
        .setName('starveall')
        .setDescription('starve all players on server'),
    async execute(interaction) {
         axios.get(`${process.env.API}:${process.env.PORT}/starve-all`
        ).then(function (response) {
            interaction.reply(response.data);
        }).catch(function (error) {
            console.log(error);
            interaction.reply("Something went wrong");
          })
        return
    },
};  